package com.someware.aoc2024.day9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(PuzzleRunner.class);
    public static final int EMPTY_BLOCK = -1;
    private final String filename;

    public PuzzleRunner(String filename) {
        this.filename = filename;
    }

    public long calculatePart1Solution() throws IOException {

        var disk = generateBlocks(readFile());
        var blocks = disk.blocks();

        var checksum = 0L;
        var leftIdx = 0;
        var rightIdx = blocks.length - 1;
        while (leftIdx < disk.filledCount()) {
            if (blocks[leftIdx] == EMPTY_BLOCK && leftIdx != rightIdx) {
                while (blocks[rightIdx] == EMPTY_BLOCK) {
                    rightIdx--;
                }
                blocks[leftIdx] = blocks[rightIdx];
                blocks[rightIdx] = EMPTY_BLOCK;
            }
            var value = leftIdx * blocks[leftIdx];
            LOGGER.debug("Adding {} to checksum of {}", value, checksum);
            checksum += value;
            leftIdx++;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Compacted block {}", blocks);
            }
        }

        return checksum;
    }

    public long calculatePart2Solution() throws IOException {
        List<File> fileList = new ArrayList<>();
        List<Free> freeList = new ArrayList<>();
        var disk = generateBlocks(readFile());

        // find all the files and free block locations
        generateFileAndFreeLists(disk, fileList, freeList );

        for (int i = 0; i < fileList.size(); i++) {
            // find first free block
            int freeIndex = findFirstFreeBlock(freeList, fileList.get(i));

            if (freeIndex >= 0) {
                // move file to new location
                var freeBlockStart = freeList.get(freeIndex).start();
                var fileId = fileList.get(i).fileId();
                var fileLength = fileList.get(i).length();
                fileList.set(i, new File(fileId, freeBlockStart, fileLength));

                // update or delete free block
                var newFreeBlockSize = freeList.get(freeIndex).length() - fileLength;
                if (newFreeBlockSize > 0) {
                    freeList.set(freeIndex, new Free(freeBlockStart + fileLength, newFreeBlockSize));
                } else {
                    freeList.remove(freeIndex);
                }
            }
        }

        long checksum = 0;

        int[] finalDisk = new int[disk.blocks().length];
        for (var file: fileList) {
            for (int i = 0; i < file.length(); i++) {
                var offset = file.start();
                finalDisk[i+offset] = file.fileId();
                checksum += (long) file.fileId() * (i + offset);
            }
        }

        return checksum;
    }

    /**
     * Find first free block that can contain a file with specific size and is before the file
     */
    private int findFirstFreeBlock(List<Free> freeList, File file) {
        for (int i = 0; i < freeList.size(); i++) {
            if (freeList.get(i).start() < file.start() && freeList.get(i).length() >= file.length()) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Build a file and free block list for the input blocks
     */
    private void generateFileAndFreeLists(Disk disk, List<File> fileList, List<Free> freeList) {
        var blocks = disk.blocks();
        int i = 0;
        while (i < blocks.length) {
            var blockId = blocks[i];
            var len = 0;
            var start = i;
            if (blockId == EMPTY_BLOCK) {
                while (i < blocks.length && blocks[i] == EMPTY_BLOCK) {
                    len++;
                    i++;
                }
                freeList.add(new Free(start, len));
            } else {
                while (i < blocks.length && blocks[i] == blockId) {
                    len++;
                    i++;
                }
                fileList.addFirst(new File(blockId, start, len));
            }
        }
    }

    private Disk generateBlocks(int[] values) {
        List<Integer> blocks = new ArrayList<>();
        int filledCount = 0;
        var filedId = 0;
        // add data blocks
        for (int i = 0; i < values.length; i += 2){
            int dataCount = values[i];
            filledCount += dataCount;
            for (int j = 0; j < dataCount; j++) {
                blocks.add(filedId);
            }
            filedId++;

            // add space
            if (i < values.length - 1) {
                int spaceCount = values[i+1];
                for (int j = 0; j < spaceCount; j++) {
                    blocks.add(EMPTY_BLOCK);
                }
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}", blocks);
            }
        }

        int[] data = new int[blocks.size()];
        for (var i = 0; i < blocks.size(); i++) {
            data[i] = blocks.get(i);
        }
        return new Disk(data, filledCount);
    }

    private int[] readFile() throws IOException {
        String str = "";
        try (var file = new BufferedReader(
                new FileReader(filename))) {
            if (file.ready()) {
                str = file.readLine();
            }

            return convertNumberStringToIntValues(str);
        }
    }

    private int[] convertNumberStringToIntValues(String s) {
        char[] chars = s.toCharArray();
        int[] intValues = new int[chars.length];

        for ( int i = 0; i < chars.length; i++ ) {
            if (Character.isDigit(chars[i])) {
                intValues[i] = (byte) (chars[i] - '0'); // Convert to numeric byte
            } else {
                throw new NumberFormatException("Character: " + chars[i] + " is not a digit");
            }
        }

        return intValues;
    }
}
