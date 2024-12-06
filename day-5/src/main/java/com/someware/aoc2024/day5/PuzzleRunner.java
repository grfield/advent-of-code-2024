package com.someware.aoc2024.day5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws IOException {
        var printer = new Printer();
        printer.readInputData(filename);
        printer.validateJobs();
        var totalOfMiddlePages = 0;
        for (var printerJob : printer.getValidJobs()) {
            var pageNumbers = printerJob.pagesNumbers();
            var middlePageNum = pageNumbers[(pageNumbers.length - 1) / 2];
            totalOfMiddlePages += middlePageNum;
        }
        return totalOfMiddlePages;
    }

    public int calculatePart2Solution() throws IOException {
        var printer = new Printer();
        printer.readInputData(filename);
        printer.validateJobs();

        Map<Integer, Set<Integer>> ruleDict = printer.getRulesAggregatedByDeps();
        var totalOfMiddlePages = 0;

        for (var printerJob : printer.getInvalidJobs()) {
            var badPages = printerJob.pagesNumbers();

            LOGGER.debug("Fixing pages: {}", badPages);
            var pageDegrees = new HashMap<Integer, Integer>();
            for (var page : badPages) {
                pageDegrees.put(page, 0);
                for (var p : badPages) {
                    if (page != p) {
                        Set<Integer> deps = ruleDict.getOrDefault(p, Set.of());
                        if (deps.contains(page)) {
                            pageDegrees.compute(page, (k, entry) -> entry + 1);
                        }
                    }
                }
            }

            var pageListWithDegrees = new ArrayList<Rule>();
            for (var pageDegreeEntry : pageDegrees.entrySet()) {
                pageListWithDegrees.add(new Rule(pageDegreeEntry.getValue(), pageDegreeEntry.getKey()));
            }
            pageListWithDegrees.sort((a, b) -> Integer.compare(a.left(), b.left()));
            int[] fixedPages = pageListWithDegrees.stream().mapToInt(Rule::right).toArray();
            LOGGER.debug("After fixing pages: {}", fixedPages);
            totalOfMiddlePages += fixedPages[(fixedPages.length - 1) / 2];
        }

        return totalOfMiddlePages;
    }
}
