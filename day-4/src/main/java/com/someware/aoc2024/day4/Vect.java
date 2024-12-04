package com.someware.aoc2024.day4;

public record Vect(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
