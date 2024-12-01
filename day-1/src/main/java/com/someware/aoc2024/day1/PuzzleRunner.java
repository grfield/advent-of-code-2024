package com.someware.aoc2024.day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);

    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculateSolution() {
        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();
        readLists(list1, list2);
        LOGGER.info("Read two lists of {} numbers", list1.size());
        return 0L;
    }

    private void readLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        try (var file = new BufferedReader(
                new FileReader(filename))) {

            while (file.ready()) {
                String[] firstValues = file.readLine().trim().split("\\s+");
                list1.add(Integer.parseInt(firstValues[0]));
                list2.add(Integer.parseInt(firstValues[1]));
            }

        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
