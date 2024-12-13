package com.someware.aoc2024.day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleRunnerTest {

    @Test
    void calculatePart1Solution1() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input1.txt" );
        var validJobsMiddlePageSum = runner.calculatePart1Solution(1);
        assertThat(validJobsMiddlePageSum).isEqualTo(7);
    }

    @Test
    void calculatePart1Solution2() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input2.txt" );
        var validJobsMiddlePageSum = runner.calculatePart1Solution(25);
        assertThat(validJobsMiddlePageSum).isEqualTo(55312);
    }

    @Test
    void calculatePart2Solution1() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input1.txt" );
        var validJobsMiddlePageSum = runner.calculatePart2Solution(1);
        assertThat(validJobsMiddlePageSum).isEqualTo(7);
    }

    @Test
    void calculatePart2Solution2() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input2.txt" );
        var validJobsMiddlePageSum = runner.calculatePart2Solution(25);
        assertThat(validJobsMiddlePageSum).isEqualTo(55312);
    }
}
