package com.someware.aoc2024.day6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.someware.aoc2024.day6.Direction.*;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws Exception {
        char[][] grid = readGridFromFile(filename);
        var pos = findGuardStartPosition(grid);
        Direction dir = UP;
        Set<Coord> locations = new HashSet<>();
        boolean exited = false;

        while (!exited) {
            var oldPos = pos;
            locations.add(oldPos);

            switch (dir) {
                case UP:
                    pos = new Coord(pos.x(), pos.y() - 1);
                    break;
                case DOWN:
                    pos = new Coord(pos.x(), pos.y() + 1);
                    break;
                case LEFT:
                    pos = new Coord(pos.x() - 1, pos.y());
                    break;
                case RIGHT:
                    pos = new Coord(pos.x() + 1, pos.y());
                    break;
            }

            if (pos.x() < 0 || pos.x() >= grid[0].length || pos.y() < 0 || pos.y() >= grid.length) {
                exited = true;
            }

            if (!exited && grid[pos.y()][pos.x()] == '#') {
                switch (dir) {
                    case UP: dir = RIGHT; break;
                    case DOWN: dir = LEFT; break;
                    case LEFT: dir = UP; break;
                    case RIGHT: dir = DOWN; break;
                }

                pos = oldPos;
            }
        }

        return locations.size();
    }

    public int calculatePart2Solution() throws IOException {
        return 0;
    }

    private Coord findGuardStartPosition(char[][] grid) throws Exception {
        for (int y = 0; y < grid.length; y++ ) {
            for (int x = 0; x < grid[0].length; x++ ) {
                if (grid[y][x] == '^') {
                    LOGGER.debug("Guard starting location is: {},{}", x, y);
                    return new Coord(x, y);
                }
            }
        }

        throw new Exception("Failed to find guard");
    }

    private char[][] readGridFromFile(String filename) throws IOException {
        List<char[]> grid = new ArrayList<>();
        try (var file = new BufferedReader(
                new FileReader(filename))) {
            while (file.ready()) {
                var line = file.readLine().trim();
                if (!line.isEmpty()) {
                    LOGGER.debug("{}", line);
                    grid.add(line.toCharArray());
                }
            }
        }

        char[][] charGrid = new char[grid.size()][grid.getFirst().length];
        for (int i = 0; i < grid.size(); i++) {
            charGrid[i] = grid.get(i);
        }

        return charGrid;
    }
}
