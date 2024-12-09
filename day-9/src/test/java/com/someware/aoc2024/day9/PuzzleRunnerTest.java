package com.someware.aoc2024.day9;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleRunnerTest {

    @Test
    void calculatePart1SolutionSmall() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-small.txt" );
        var answer = runner.calculatePart1Solution();
        assertThat(answer).isEqualTo(60);
    }

    @Test
    void calculatePart1Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input.txt" );
        var answer = runner.calculatePart1Solution();
        assertThat(answer).isEqualTo(1928);
    }

    @Test
    void calculatePart2Solution() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input.txt" );
        var answer = runner.calculatePart2Solution();
        assertThat(answer).isEqualTo(2858);
    }

    @Test
    void calculatePart2Solution_1() throws IOException {
        var runner = new PuzzleRunner("src/test/resources/test-input-part2-1.txt" );
        var answer = runner.calculatePart2Solution();
        assertThat(answer).isEqualTo(307);
    }
}
