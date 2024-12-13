package com.someware.aoc2024.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution(int blinkCount) throws IOException {
        List<Long> stones = readFile();

        for (int i = 0; i < blinkCount; i++) {
            int index = 0;
            while (index < stones.size()) {
                long stoneNum = stones.get(index);
                String stoneStr = stones.get(index).toString();
                if (stoneNum == 0L) {
                    stones.set(index, 1L);
                } else if (stoneStr.length() % 2 == 0) {
                    int mid = stoneStr.length() / 2 - 1;
                    stones.set(index, Long.parseLong(stoneStr.substring(mid + 1)));
                    stones.add(index, Long.parseLong(stoneStr.substring(0, mid + 1)));
                    index++;
                } else {
                    stones.set(index, stoneNum * 2024);
                }

                index++;
            }
        }

        return stones.size();
    }

    public long calculatePart2Solution(int blinkCount) throws IOException {
        long startTime = System.nanoTime();

        List<Long> stones = readFile();
        Map<CountSteps, Long> cache = new HashMap<>();

        long sum = 0L;
        for (var num : stones) {
            sum += countStones(num, blinkCount, cache);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        LOGGER.info("Processing for part 2 took {} ms", duration / 1000000);
        LOGGER.info("Cache used for part 2 contains {} entries", cache.size());
        return sum;
    }

    private long countStones(long num, int count, Map<CountSteps, Long> cache) {
        // base case
        if (count == 0) {
            return 1;
        }

        // check cache first
        CountSteps x = new CountSteps(num, count);
        if (cache.containsKey(x)) {
            return cache.get(x);
        }

        // not in cache
        long sum;
        String stoneStr = String.valueOf(num);

        if (num == 0L) {
            sum = countStones(1, count - 1, cache);
        } else if (stoneStr.length() % 2 == 0) {
            int mid = stoneStr.length() / 2 - 1;
            sum = countStones(Long.parseLong(stoneStr.substring(0, mid + 1)), count - 1, cache) +
                  countStones(Long.parseLong(stoneStr.substring(mid + 1)), count - 1, cache);
        } else {
            sum = countStones(num * 2024, count - 1, cache);
        }

        // store result in cache
        cache.put(x, sum);

        return sum;
    }

    private List<Long> readFile() throws IOException {
        List<Long> stones  =new ArrayList<>();
        try (var file = new BufferedReader(
                new FileReader(filename))) {
            if (file.ready()) {
                var tokens = file.readLine().trim().split(" ");
                for (var token : tokens) {
                    stones.add(Long.parseLong(token));
                }
            }

            return stones;
        }
    }
}
