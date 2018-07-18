package com.practice.fmamic.part3;

public class Knapsack {

    long maximumKnapsackVauleNaive(int[] values, int[] weights, int total) {
        return maximumKnapsackVauleNaiveR(values, weights, 0, 0, total, 0);
    }

    private long maximumKnapsackVauleNaiveR(int[] values, int[] weights, int position, int current, int total, int value) {

        if (position >= values.length)
            return 0;

        if (current > total)
            return value;

        return Math.max(maximumKnapsackVauleNaiveR(values, weights, position+1, current+weights[position], total, values[position] + value),
                maximumKnapsackVauleNaiveR(values, weights, position+1, current, total, value));
    }

}
