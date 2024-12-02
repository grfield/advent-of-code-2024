package com.someware.aoc2024.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PuzzleTable {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleTable.class);
    public static final String OUTPUT_STRING = "{}  ..... {}";
    private final List<List<Integer>> table;

    public PuzzleTable() {
        this.table = new ArrayList<>(new ArrayList<>());
    }

    public void readFromFile(String filename) throws IOException {
        try (var file = new BufferedReader(
                new FileReader(filename))) {

            while (file.ready()) {
                String[] strings = file.readLine().trim().split("\\s+");
                var values = Arrays.stream(strings)
                        .map(Integer::parseInt)
                        .toList();
                table.add(new ArrayList<>(values));
            }
        }
    }

    public int safeReportCount(boolean applyProblemDamper) {
        int safeReportCount = 0;
        for (var report : this.table) {
            if (isSafe(report)) {
                safeReportCount++;
                LOGGER.debug(OUTPUT_STRING, report, "SAFE");
            } else if (applyProblemDamper && isSafeWithDampener(report)) {
                safeReportCount++;
                LOGGER.debug(OUTPUT_STRING, report, "SAFE (d)");
            } else {
                LOGGER.debug(OUTPUT_STRING, report, "UNSAFE");
            }
        }
        return safeReportCount;
    }

    private boolean isSafeWithDampener(List<Integer> report) {
        boolean isReallySafe = false;
        for (var i = 0; i < report.size(); i++) {
            var testReport = new ArrayList<>(report);
            testReport.remove(i);
            if (isSafe(testReport)) {
                isReallySafe = true;
            }
        }

        return isReallySafe;
    }

    private boolean isSafe(List<Integer> report) {
        boolean isSafe = true;
        boolean firstValue = true;
        int prevValue = 0;
        Optional<Boolean> isIncreasing = Optional.empty();

        for (var value : report) {
            if (!firstValue) {
                int absDiff = Math.abs(value - prevValue);
                if ( absDiff > 3 || absDiff < 1 ) {
                    isSafe = false;
                }
                int diff = (value - prevValue);
                if (isIncreasing.isEmpty()) {
                    isIncreasing = Optional.of(diff > 0);
                } else {
                    if (Boolean.TRUE.equals(isIncreasing.get() != diff > 0)) {
                        isSafe = false;
                    }
                }
            }

            firstValue = false;
            prevValue = value;
        }

        return isSafe;
    }
}
