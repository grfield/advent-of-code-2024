package com.someware.aoc2024.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Printer {
    private final List<Job> jobs;
    private final List<Rule> rules;

    public Printer() {
        this.rules = new ArrayList<>();
        this.jobs = new ArrayList<>();
    }

    public void readInputData(String filename) throws IOException {
        try (var file = new BufferedReader(
                new FileReader(filename))) {
            readRules(file);
            readJobs(file);
        }
    }

    private void readJobs(BufferedReader file) throws IOException {
        while (file.ready()) {
            var line = file.readLine().trim();
            if (!line.isEmpty()) {
                String[] tokens = line.split(",");
                int[] pageNumbers = new int[tokens.length];
                var pagePositions = new HashMap<Integer, Integer>();
                for (int i = 0; i < tokens.length; i++) {
                    var page = Integer.parseInt(tokens[i]);
                    pagePositions.put(page, i);
                    pageNumbers[i] = page;
                }

                this.jobs.add(new Job(pageNumbers, pagePositions));
            }
        }
    }

    private void readRules(BufferedReader file) throws IOException {
        while (file.ready()) {
            var line = file.readLine().trim();
            if (!line.isEmpty()) {
                String[] tokens = line.split("\\|");
                this.rules.add(new Rule(
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[1])));
            } else {
                break;
            }
        }
    }

    public List<int[]> validJobs() {
        ArrayList<int[]> validJobs = new ArrayList<>();

        for (var job : this.jobs) {
            var validJob = true;
            for (var rule : this.rules) {
                var leftRulePagePos = job.pagePositions().get(rule.left());
                var rightRulePagePos = job.pagePositions().get(rule.right());
                if ( leftRulePagePos != null && rightRulePagePos != null &&
                        leftRulePagePos >= rightRulePagePos ) {
                    validJob = false;
                }
            }
            if (validJob) {
                validJobs.add(job.pagesNumbers());
            }
        }

        return validJobs;

    }
}
