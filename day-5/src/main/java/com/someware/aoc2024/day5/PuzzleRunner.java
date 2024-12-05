package com.someware.aoc2024.day5;

import java.io.IOException;

public class PuzzleRunner {
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws IOException {
        var printer = new Printer();
        printer.readInputData(filename);
        var validPrintJobs = printer.validJobs();
        var totalOfMiddlePages = 0;
        for (var printerJob : validPrintJobs) {
            var middlePageNum = printerJob[(printerJob.length - 1) / 2];
            totalOfMiddlePages += middlePageNum;
        }
        return totalOfMiddlePages;
    }

    public int calculatePart2Solution() {
        return 0;
    }
}
