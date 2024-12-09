package com.someware.aoc2024.day9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {

        var disk = generateBlocks(readFile());
        var blocks = disk.blocks();

        var checksum = 0L;
        var leftIdx = 0;
        var rightIdx = blocks.length - 1;
        while (leftIdx < disk.filledCount()) {
            if (blocks[leftIdx] == '.' && leftIdx != rightIdx) {
                while (blocks[rightIdx] == '.') {
                    rightIdx--;
                }
                blocks[leftIdx] = blocks[rightIdx];
                blocks[rightIdx] = '.';
            }
            var value = leftIdx * (blocks[leftIdx] - '0');
            LOGGER.debug("Adding {} to checksum of {}", value, checksum);
            checksum += value;
            leftIdx++;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Compacted block {}", charArrayAsString(blocks));
            }
        }

        return checksum;
    }

    public long calculatePart2Solution() throws IOException {
        return -1;
    }

    private Disk generateBlocks(int[] values) {
        List<Character> blocks = new ArrayList<>();
        int filledCount = 0;
        var index = 0;
        // add data blocks
        for (int i = 0; i < values.length; i += 2){
            int dataCount = values[i];
            filledCount += dataCount;
            for (int j = 0; j < dataCount; j++) {
                blocks.add((char) (index + '0'));
            }
            index++;

            // add space
            if (i < values.length - 1) {
                int spaceCount = values[i+1];
                for (int j = 0; j < spaceCount; j++) {
                    blocks.add('.');
                }
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}", charArrayAsString(blocks));
            }
        }

        return new Disk(blocks.stream()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .toCharArray(), filledCount);
    }

    private int[] readFile() throws IOException {
        String str = "";
        try (var file = new BufferedReader(
                new FileReader(filename))) {
            if (file.ready()) {
                str = file.readLine();
            }

            return convertNumberStringToIntValues(str);
        }
    }


    private String charArrayAsString(List<Character> blocks) {
        StringBuilder builder = new StringBuilder();
        blocks.forEach(builder::append);
        return builder.toString();
    }

    private String charArrayAsString(char[] blocks) {
        StringBuilder builder = new StringBuilder();
        for (char block : blocks) {
            builder.append(block);
        }
        return builder.toString();
    }

    private int[] convertNumberStringToIntValues(String s) {
        char[] chars = s.toCharArray();
        int[] intValues = new int[chars.length];

        for ( int i = 0; i < chars.length; i++ ) {
            if (Character.isDigit(chars[i])) {
                intValues[i] = (byte) (chars[i] - '0'); // Convert to numeric byte
            } else {
                throw new NumberFormatException("Character: " + chars[i] + " is not a digit");
            }
        }

        return intValues;
    }
}
