package com.someware.aoc2024.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);

    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() {
        final var table = new PuzzleTable();
        try {
            table.readFromFile(filename);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

        return table.safeReportCount(false);
    }

    public int calculatePart2Solution() {
        final var table = new PuzzleTable();
        try {
            table.readFromFile(filename);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

        return table.safeReportCount(true);
    }
}
