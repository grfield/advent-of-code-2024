package com.someware.aoc2024.day13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private static final String REGEX_EQ = "=";
    private static final String REGEX_PLUS = "\\+";
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {
        var c = readInput();

        return 0;
    }

    public long calculatePart2Solution() throws IOException {

        return 0;
    }

    private List<Prize> readInput() throws IOException {
        List<Prize> prizeList = new ArrayList<>();

        try (var file = new BufferedReader(new FileReader(filename))) {
            String content = file.lines()
                    .reduce("", (acc, line) -> acc + (line.isBlank() ? "\n" : line + "\n"));
            var blockArray = content.split("\n\n");
            Arrays.stream(blockArray).forEach(block -> {
                var tokens = block.split("[:,\n]");
                Prize prize = new Prize(
                        new Vec(extractRightParam(tokens[1], REGEX_PLUS), extractRightParam(tokens[2], REGEX_PLUS)),
                        new Vec(extractRightParam(tokens[4], REGEX_PLUS), extractRightParam(tokens[5], REGEX_PLUS)),
                        new Vec(extractRightParam(tokens[7], REGEX_EQ), extractRightParam(tokens[8], REGEX_EQ)));
                prizeList.add(prize);
            });

            return prizeList;
        }
    }

    private int extractRightParam(String token, String operator) {
        return Integer.parseInt(token.split(operator)[1]);
    }
}
