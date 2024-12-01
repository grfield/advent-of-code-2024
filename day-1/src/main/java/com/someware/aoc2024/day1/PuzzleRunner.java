package com.someware.aoc2024.day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);

    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() {
        var puzzleInput = new PuzzleInput(new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        try {
            puzzleInput.read(filename);
            LOGGER.info("Part I: Read puzzle input size of {} numbers", puzzleInput.size());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return puzzleInput.sumOfDiffs();
    }

    public int calculatePart2Solution() {
        var puzzleInput = new PuzzleInput(new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        try {
            puzzleInput.read(filename);
            LOGGER.info("Part II: Read puzzle input size of {} numbers", puzzleInput.size());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return puzzleInput.similarityScore();
    }
}
