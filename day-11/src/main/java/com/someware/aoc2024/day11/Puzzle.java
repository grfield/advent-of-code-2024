package com.someware.aoc2024.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Puzzle {
    static final Logger LOGGER = LoggerFactory.getLogger(Puzzle.class);
    static final String PUZZLE_INPUT = "day-11/src/main/resources/puzzle-input.txt";

    public static void main(String[] args) throws IOException {
        LOGGER.info("AoC2024: Day 11");
        var runner = new PuzzleRunner(PUZZLE_INPUT);
        LOGGER.info("...part 1 solution: {}", runner.calculatePart1Solution());
        LOGGER.info("...part 2 solution: {}", runner.calculatePart2Solution());
    }
}
