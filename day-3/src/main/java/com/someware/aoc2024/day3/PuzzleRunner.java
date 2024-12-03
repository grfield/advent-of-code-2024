package com.someware.aoc2024.day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    static final String REGEX_PART1 = "mul\\([0-9]{1,3}\\,[0-9]{1,3}\\)";
    static final String REGEX_PART2 = "((don't\\(\\))|(do\\(\\))|mul\\([0-9]{1,3}\\,[0-9]{1,3}\\))";

    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public int calculatePart1Solution() throws IOException {
        var input = readFileAsString();
        var commands = extractValidCommands(input, REGEX_PART1);
        var operators = parseCommandsToOperators(commands);
        return operators.stream().mapToInt(o -> o.left() * o.right()).sum();
    }

    public int calculatePart2Solution() throws IOException {
        var input = readFileAsString();
        var commands = extractValidCommands(input, REGEX_PART2);
        var operators = parseCommandsToOperators(commands);

        var sum = 0;
        var enabled = true;
        for (Operator operator : operators) {
            switch (operator.func()) {
                case "don't": enabled = false; break;
                case "do": enabled = true; break;
                case "mul": if (enabled) sum += operator.left() * operator.right(); break;
                default: LOGGER.error("Invalid operator: {}", operator);
            }
        }
        return sum;
    }

    private List<Operator> parseCommandsToOperators(List<String> commands) {
        List<Operator> operators = new ArrayList<>();

        for (String command : commands) {
            var tokens = command.split("[,()]");
            if (tokens[0].equals("mul")) {
                operators.add(new Operator(
                        tokens[0],
                        Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2])));
            } else {
                operators.add(new Operator(tokens[0], 0,0));
            }
        }

        return operators;
    }

    private List<String> extractValidCommands(String input, String regex) {
        List<String> commands = new ArrayList<>();
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(input);

        while (matcher.find()) {
            var start = matcher.start();
            var end = matcher.end();
            var command = input.substring(start, end);
            commands.add(command);
            LOGGER.debug("Found operator: {}", command);
        }

        return commands;
    }

    private String readFileAsString() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (var file = new BufferedReader(
                new FileReader(filename))) {

            while (file.ready()) {
                builder.append(file.readLine());
            }
        }

        return builder.toString();
    }
}
