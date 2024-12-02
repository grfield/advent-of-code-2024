package com.someware.aoc2024.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Puzzle {
    static final Logger LOGGER = LoggerFactory.getLogger(Puzzle.class);
    static final String PUZZLE_INPUT = "day-2/src/main/resources/puzzle-input.txt";

    public static void main(String[] args) {
        LOGGER.info("AoC2024: Day 2");
        var runner = new PuzzleRunner(PUZZLE_INPUT);
        LOGGER.info("...part 1 solution: {}", runner.calculatePart1Solution());
        LOGGER.info("...part 2 solution: {}", runner.calculatePart2Solution());
    }
}
