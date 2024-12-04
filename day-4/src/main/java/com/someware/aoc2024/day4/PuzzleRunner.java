package com.someware.aoc2024.day4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    static final String SEARCH_WORD_1 = "XMAS";
    static final String SEARCH_WORD_2 = "MAS";
    private final String filename;

    static final Vect[] searchVectors = {
            new Vect(1,0),
            new Vect(-1,0),
            new Vect(0,1),
            new Vect(0,-1),
            new Vect(1,1),
            new Vect(-1,1),
            new Vect(1,-1),
            new Vect(-1,-1)
    };

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws IOException {
        var grid = readFileAsGrid();
        var count = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == SEARCH_WORD_1.charAt(0)) {
                    count += findWordInstances(SEARCH_WORD_1, grid, row, col, Optional.empty());
                }
            }
            LOGGER.debug("Count after row {} = {}", row, count);
        }

        return count;
    }

    public int calculatePart2Solution() throws IOException {
        var grid = readFileAsGrid();
        var count = 0;

        var wordCentres = new HashMap<String, Integer>();

        // count total occurrences of MAS
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == SEARCH_WORD_2.charAt(0)) {
                    count += findWordInstances(SEARCH_WORD_2, grid, row, col, Optional.of(wordCentres));
                }
            }
            LOGGER.debug("Count after row {} = {}", row, count);
        }

        // count number of overlapping centres
        int overlappingWords = 0;
        for (var c : wordCentres.values()) {
            if (c == 2) overlappingWords++;
        }

        return overlappingWords;
    }

    private int findWordInstances(String searchWord, char[][] grid, int row, int col,
                                  Optional<Map<String, Integer>> wordCentres) {
        final var gridHeight = grid.length;
        final var gridWidth = grid[0].length;

        final var wordMiddleLetter = searchWord.charAt((searchWord.length() - 1) / 2);

        var count = 0;

        for (var searchVector : searchVectors) {
            int x = col;
            int y = row;
            boolean found = true;
            String centreLetterCoord = "";
            for (char c : searchWord.toCharArray()) {
                if (x < 0 || x > gridWidth - 1 || y < 0 || y > gridHeight - 1 || c != grid[y][x])
                {
                    found = false;
                    break;
                }

                if (wordCentres.isPresent() && c == wordMiddleLetter) {
                    centreLetterCoord = new Vect(x, y).toString();
                }

                x += searchVector.x();
                y += searchVector.y();
            }

            if (found) {
                count++;
                // only looking for diagonal vectors
                if (wordCentres.isPresent() && searchVector.x() != 0 && searchVector.y() != 0) {
                    int c = wordCentres.get().getOrDefault(centreLetterCoord, 0) + 1;
                    LOGGER.debug("Vector centre: {}", centreLetterCoord);
                    wordCentres.get().put(centreLetterCoord, c);
                }
            }
        }

        return count;
    }

    private char[][] readFileAsGrid() throws IOException {
        ArrayList<String> rows = new ArrayList<>();
        try (var file = new BufferedReader(
                new FileReader(filename))) {

            while (file.ready()) {
                rows.add(file.readLine());
            }
        }

        char[][] grid = new char[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            grid[i] = rows.get(i).toCharArray();
        }

        return grid;
    }
}
