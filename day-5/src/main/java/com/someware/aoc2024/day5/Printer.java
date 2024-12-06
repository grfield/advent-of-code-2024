package com.someware.aoc2024.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Printer {
    private final List<Job> jobs;
    private final List<Rule> rules;
    private final List<Job> validJobs;
    private final List<Job> invalidJobs;

    public Printer() {
        this.rules = new ArrayList<>();
        this.jobs = new ArrayList<>();
        this.validJobs = new ArrayList<>();
        this.invalidJobs = new ArrayList<>();
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

    public void validateJobs() {
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
                validJobs.add(job);
            } else {
                invalidJobs.add(job);
            }
        }
    }

    public List<Job> getValidJobs() {
        return validJobs;
    }

    public List<Job> getInvalidJobs() {
        return invalidJobs;
    }

    public Map<Integer, Set<Integer>> getRulesAggregatedByDeps() {
        var ruleDict = new HashMap<Integer, Set<Integer>>();
        for (var rule : this.rules) {
            var dependentSet = ruleDict.getOrDefault(rule.left(), new HashSet<>());
            ruleDict.put(rule.left(), dependentSet);
            dependentSet.add(rule.right());
        }
        return ruleDict;
    }
}
