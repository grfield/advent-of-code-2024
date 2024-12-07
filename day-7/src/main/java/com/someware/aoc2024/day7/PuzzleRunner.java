package com.someware.aoc2024.day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {
        // read input
        List<Equation> input = readInput();
        long solution = 0L;

        // for each entry
        for (var line : input) {
            long answer = line.total();
            var list = line.operands();
            char[] arr = new char[list.size() - 1];
            List<char[]> perms = new ArrayList<>();
            generateAllOpPerms(list.size() - 1, arr, 0, perms);

            boolean validSum = false;
            for (var perm : perms) {
                if (answer == applyOpsToNumbers(perm, list))
                    validSum = true;
            }

            if (validSum) {
                solution += answer;
            }
        }

        return solution;
    }

    public int calculatePart2Solution() {
        return 0;
    }

    private List<Equation> readInput() throws IOException {
        List<Equation> input = new ArrayList<>();

        try (var file = new BufferedReader(
                new FileReader(filename))) {
            while (file.ready()) {
                var line = file.readLine();
                var tokens = line.split("[: ]");
                List<Long> list = new ArrayList<>();
                for (int i = 2; i < tokens.length; i++) {
                    list.add(Long.parseLong(tokens[i]));
                }
                input.add(new Equation(Long.parseLong(tokens[0]), list));
                LOGGER.debug("{}: {}", Long.parseLong(tokens[0]), list);
            }
        }

        return input;
    }

    static void generateAllOpPerms(int n, char[] arr, int i, List<char[]> perms)
    {
        if ( i == n) {
            perms.add(arr.clone());
            return;
        }

        arr[i] = '+';
        generateAllOpPerms(n, arr, i + 1, perms);
        arr[i] = '*';
        generateAllOpPerms(n, arr, i + 1, perms);
    }

    private long applyOpsToNumbers(char[] perm, List<Long> list) {
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
}
