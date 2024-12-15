package com.someware.aoc2024.day10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {
        var grid = readGridFromFile(filename);

        var peakCount = 0;
        for (int y = 0; y < grid.length; y++ ) {
            for (int x = 0; x < grid[0].length; x++ ) {
                if (grid[y][x] == 0) {
                    var peaks = new HashSet<Peak>();
                    LOGGER.debug("Found trail head at: {},{}", x, y);
                    findUniquePeakCount(grid, x, y, 0, peaks);
                    LOGGER.debug("Found {} unique peaks", peaks.size());
                    peakCount += peaks.size();
                    LOGGER.debug("Found {} total peaks", peakCount);
                }
            }
        }

        return peakCount;
    }

    public long calculatePart2Solution() throws IOException {
        var grid = readGridFromFile(filename);

        var peakCount = 0;
        for (int y = 0; y < grid.length; y++ ) {
            for (int x = 0; x < grid[0].length; x++ ) {
                if (grid[y][x] == 0) {
                    LOGGER.debug("Found trail head at: {},{}", x, y);
                    peakCount += findPeakCount(grid, x, y, 0);
                }
            }
        }

        return peakCount;
    }

    private void findUniquePeakCount(int[][] grid, int x, int y, int level, Set<Peak> peaks) {

        if (level == 9) {
            peaks.add(new Peak(x, y));
        }

        int nextLevel = level + 1;

        if (x > 0 && grid[y][x - 1] == nextLevel) {
            findUniquePeakCount(grid, x - 1, y, nextLevel, peaks);
        }

        if (x < grid[0].length - 1 && grid[y][x + 1] == nextLevel) {
            findUniquePeakCount(grid, x + 1, y, nextLevel, peaks);
        }

        if (y > 0 && grid[y - 1][x] == nextLevel) {
            findUniquePeakCount(grid, x, y - 1, nextLevel, peaks);
        }

        if (y < grid.length - 1 && grid[y + 1][x] == nextLevel) {
            findUniquePeakCount(grid, x, y + 1, nextLevel, peaks);
        }
    }

    private int findPeakCount(int[][] grid, int x, int y, int level) {
        int peakCount = 0;

        if (level == 9) {
            peakCount++;
        }

        int nextLevel = level + 1;

        if (x > 0 && grid[y][x - 1] == nextLevel) {
            peakCount += findPeakCount(grid, x - 1, y, nextLevel);
        }

        if (x < grid[0].length - 1 && grid[y][x + 1] == nextLevel) {
            peakCount += findPeakCount(grid, x + 1, y, nextLevel);
        }

        if (y > 0 && grid[y - 1][x] == nextLevel) {
            peakCount += findPeakCount(grid, x, y - 1, nextLevel);
        }

        if (y < grid.length - 1 && grid[y + 1][x] == nextLevel) {
            peakCount += findPeakCount(grid, x, y + 1, nextLevel);
        }

        return peakCount;
    }

    private int[][] readGridFromFile(String filename) throws IOException {
        List<int[]> grid = new ArrayList<>();
        try (var file = new BufferedReader(
                new FileReader(filename))) {
            while (file.ready()) {
                var line = file.readLine().trim();
                if (!line.isEmpty()) {
                    LOGGER.debug("{}", line);
                    var chars = line.toCharArray();
                    var integers = new int[chars.length];
                    for (int i = 0; i < chars.length; i++) {
                        integers[i] = chars[i] - '0';
                    }
                    grid.add(integers);
                }
            }
        }

        int[][] intGrid = new int[grid.size()][grid.getFirst().length];
        for (int i = 0; i < grid.size(); i++) {
            intGrid[i] = grid.get(i);
        }

        return intGrid;
    }
}
