package com.someware.aoc2024.day3;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class PuzzleRunnerTest {

    @Test
    void calculatePart1Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-part1.txt" );
        var safeReports = runner.calculatePart1Solution();
        assertThat(safeReports).isEqualTo(161);
    }

    @Test
    void calculatePart2Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-part2.txt" );
        var similarityScore = runner.calculatePart2Solution();
        assertThat(similarityScore).isEqualTo(48);
    }
}
