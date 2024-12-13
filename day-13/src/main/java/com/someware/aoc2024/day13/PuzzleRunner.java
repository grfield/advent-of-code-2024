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
    private static final long PART2_SCALE = 10000000000000L;
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {
        var prizes = readInput(0L);

        return calculateTokensUsed(prizes);
    }

    public long calculatePart2Solution() throws IOException {
        var prizes = readInput(PART2_SCALE);

        return calculateTokensUsed(prizes);
    }

    private static long calculateTokensUsed(List<Prize> prizes) {
        var totalTokens = 0L;

        long startTime = System.nanoTime();

        for (var p : prizes) {
            var bFactor = p.buttonB().x() * p.buttonA().y() - p.buttonA().x() * p.buttonB().y();
            var pFactor = p.buttonA().y() * p.prize().x() - p.buttonA().x() * p.prize().y();
            if (pFactor % bFactor == 0) {
                var bCount = pFactor / bFactor;
                var calc = p.prize().x() - p.buttonB().x() * bCount;
                if ( calc % p.buttonA().x() == 0) {
                    var aCount = calc / p.buttonA().x();
                    totalTokens += aCount * 3L + bCount;
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        LOGGER.info("Processing took {} ns", duration);

        return totalTokens;
    }

    private List<Prize> readInput(long scaleFactor) throws IOException {
        List<Prize> prizeList = new ArrayList<>();

        try (var file = new BufferedReader(new FileReader(filename))) {
            String content = file.lines()
                    .reduce("", (acc, line) -> acc + (line.isBlank() ? "\n" : line + "\n"));
            var blockArray = content.split("\n\n");
            Arrays.stream(blockArray).forEach(block -> {
                var tokens = block.split("[:,\n]");
                Prize prize = new Prize(
                        new Vec(extractRightParam(tokens[1], REGEX_PLUS),
                                extractRightParam(tokens[2], REGEX_PLUS)),
                        new Vec(extractRightParam(tokens[4], REGEX_PLUS),
                                extractRightParam(tokens[5], REGEX_PLUS)),
                        new Vec(extractRightParam(tokens[7], REGEX_EQ) + scaleFactor,
                                extractRightParam(tokens[8], REGEX_EQ) + scaleFactor));
                prizeList.add(prize);
            });

            return prizeList;
        }
    }

    private long extractRightParam(String token, String operator) {
        return Long.parseLong(token.split(operator)[1]);
    }
}
