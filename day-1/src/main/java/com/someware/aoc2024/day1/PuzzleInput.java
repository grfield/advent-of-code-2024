package com.someware.aoc2024.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public record PuzzleInput(
        ArrayList<Integer> leftList,
        ArrayList<Integer> rightList,
        HashMap<Integer, Integer> occurrences)
{
    public int size() {
        return leftList.size();
    }

    public int sumOfDiffs() {
        int sumOfDiffs = 0;
        Collections.sort(leftList);
        Collections.sort(rightList);
        for (int i = 0; i < size(); i++) {
            sumOfDiffs += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return sumOfDiffs;
    }

    public int similarityScore() {
        int similarityScore = 0;
        for (int i = 0; i < size(); i++) {
            var leftValue = leftList.get(i);
            similarityScore += leftValue * occurrences.getOrDefault(leftValue, 0);
        }

        return similarityScore;
    }

    public void readFromFile(String filename) throws IOException {
        try (var file = new BufferedReader(
                new FileReader(filename))) {

            while (file.ready()) {
                String[] firstValues = file.readLine().trim().split("\\s+");
                var leftValue = Integer.parseInt(firstValues[0]);
                var rightValue = Integer.parseInt(firstValues[1]);
                leftList.add(leftValue);
                rightList.add(rightValue);
                occurrences.put(rightValue,occurrences.getOrDefault(rightValue, 0) + 1);
            }
        }
    }
}
