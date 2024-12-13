package com.someware.aoc2024.day13;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleRunnerTest {

    @Test
    void calculatePart1Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input.txt" );
        var answer = runner.calculatePart1Solution();
        assertThat(answer).isEqualTo(480);
    }

    @Test
    void calculatePart2Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input.txt" );
        var answer = runner.calculatePart2Solution();
        assertThat(answer).isEqualTo(875318608908L);
    }
}
