package com.someware.aoc2024.day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Puzzle {
    static final Logger LOGGER = LoggerFactory.getLogger(Puzzle.class);
    static final String PUZZLE_INPUT = "day-1/src/main/resources/puzzle-input.txt";

    public static void main(String[] args) {
        LOGGER.info("AoC2024: Day 1");
        var runner = new PuzzleRunner(PUZZLE_INPUT);
        LOGGER.info("Day 1 solution: {}", runner.calculateSolution());
    }
}
