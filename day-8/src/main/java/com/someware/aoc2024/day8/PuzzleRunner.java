package com.someware.aoc2024.day8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {


        return 0;
    }

    public long calculatePart2Solution() throws IOException {

        return 0;
    }


    private long applyTwoOpsToNumbers(char[] perm, List<Long> list) {
        long ans = list.getFirst();
        for (int i = 0; i < perm.length; i++) {
            ans = switch (perm[i]) {
                case '+': yield ans + list.get(i+1);
                case '*': yield ans * list.get(i+1);
                default: yield 0;
            };
        }

        return ans;
    }

    private long applyThreeOpsToNumbers(char[] perm, List<Long> list) {
        long ans = list.getFirst();
        for (int i = 0; i < perm.length; i++) {
            ans = switch (perm[i]) {
                case '+': yield ans + list.get(i+1);
                case '*': yield ans * list.get(i+1);
                case '|': {
                    String left = String.valueOf(ans);
                    String right = String.valueOf(list.get(i+1));
                    yield Long.parseLong(left + right);
                }
                default: yield 0;
            };
        }

        return ans;
    }
}
