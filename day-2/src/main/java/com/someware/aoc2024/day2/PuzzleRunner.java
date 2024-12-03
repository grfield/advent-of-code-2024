package com.someware.aoc2024.day2;

import java.io.IOException;

public class PuzzleRunner {
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws IOException {
        final var table = new PuzzleTable();
        table.readFromFile(filename);

        return table.safeReportCount(false);
    }

    public int calculatePart2Solution() throws IOException {
        final var table = new PuzzleTable();
        table.readFromFile(filename);

        return table.safeReportCount(true);
    }
}
