package com.someware.aoc2024.day4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    static final String SEARCH_WORD = "XMAS";
    private final String filename;

    static final SearchVector[] searchVectors = {
            new SearchVector(1,0),
            new SearchVector(-1,0),
            new SearchVector(0,1),
            new SearchVector(0,-1),
            new SearchVector(1,1),
            new SearchVector(-1,1),
            new SearchVector(1,-1),
            new SearchVector(-1,-1)
    };

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws IOException {
        var grid = readFileAsGrid();
        var count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 'X') {
                    count += findWordInstances(grid, row, col);
                }
            }
            LOGGER.debug("Count after row {} = {}", row, count);
        }
        return count;
    }

    public int calculatePart2Solution()  {
        return 0;
    }

    private int findWordInstances(char[][] grid, int row, int col) {
        final var gridHeight = grid.length;
        final var gridWidth = grid[0].length;

        var count = 0;

        for (var searchVector : searchVectors) {
            int x = col;
            int y = row;
            boolean found = true;
            for (char c : SEARCH_WORD.toCharArray()) {
                if (x < 0 || x > gridWidth - 1 || y < 0 || y > gridHeight - 1 || c != grid[y][x])
                {
                    found = false;
                    break;
                }

                x += searchVector.x();
                y += searchVector.y();
            }

            if (found) count++;
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
