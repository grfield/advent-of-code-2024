package com.someware.aoc2024.day10;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleRunnerTest {

    @Test
    void calculatePart1Solution1() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-1.txt" );
        var validJobsMiddlePageSum = runner.calculatePart1Solution();
        assertThat(validJobsMiddlePageSum).isEqualTo(3);
    }

    @Test
    void calculatePart1Solution2() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-2.txt" );
        var validJobsMiddlePageSum = runner.calculatePart1Solution();
        assertThat(validJobsMiddlePageSum).isEqualTo(36);
    }

    @Test
    void calculatePart2Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-2.txt" );
        var validJobsMiddlePageSum = runner.calculatePart2Solution();
        assertThat(validJobsMiddlePageSum).isEqualTo(81);
    }
}
