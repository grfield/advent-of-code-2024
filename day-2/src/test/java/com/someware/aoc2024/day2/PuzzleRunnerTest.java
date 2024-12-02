package com.someware.aoc2024.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PuzzleRunnerTest {

    @Test
    void calculatePart1Solution() {
        var runner = new PuzzleRunner("src/test/resources/test-input.txt" );
        var safeReports = runner.calculatePart1Solution();
        assertThat(safeReports).isEqualTo(2);
    }

    @Test
    void calculatePart2Solution() {
        var runner = new PuzzleRunner("src/test/resources/test-input.txt" );
        var similarityScore = runner.calculatePart2Solution();
        assertThat(similarityScore).isZero();
    }
}
