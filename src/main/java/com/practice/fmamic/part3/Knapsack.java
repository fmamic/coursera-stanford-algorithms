package com.practice.fmamic.part3;

class Knapsack {

    // time O(2^n) space recursive stack O(n)
    int maximumKnapsackValueNaive(int[] values, int[] weights, int total) {
        return maximumKnapsackValueNaiveR(values, weights, 0, 0, total);
    }

    private int maximumKnapsackValueNaiveR(int[] values, int[] weights, int position, int current, int total) {

        if (position >= values.length)
            return 0;

        if (current + weights[position] > total) {
            return maximumKnapsackValueNaiveR(values, weights, position + 1, current, total);
        }

        return Math.max(values[position] + maximumKnapsackValueNaiveR(values, weights, position + 1, current + weights[position], total),
                maximumKnapsackValueNaiveR(values, weights, position + 1, current, total));
    }

    // time O(vW) space O(nW) + recursion stack O(n)
    int maximumKnapsackValueMemo(int[] values, int[] weights, int total) {
        int[][] memo = new int[values.length][total + 1];
        return maximumKnapsackValueMemoR(values, weights, 0, 0, total, memo);
    }

    private int maximumKnapsackValueMemoR(int[] values, int[] weights, int position, int current, int total, int[][] memo) {

        if (position >= values.length)
            return 0;

        if (memo[position][current] != 0)
            return memo[position][current];

        if (current + weights[position] > total) {
            memo[position][current] = maximumKnapsackValueMemoR(values, weights, position + 1, current, total, memo);
            return memo[position][current];
        }

        memo[position][current] = Math.max(values[position] + maximumKnapsackValueMemoR(values, weights, position + 1, current + weights[position], total, memo),
                maximumKnapsackValueMemoR(values, weights, position + 1, current, total, memo));

        return memo[position][current];
    }

    // time O(vW) space O(nW)
    int maximumKnapsackValueDp(int[] values, int[] weights, int total) {

        if (total == 0)
            return 0;

        int[] memo1 = new int[total + 1];
        int[] memo2 = new int[total + 1];

        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j - weights[i] < 0) {
                    memo2[j] = memo1[j];
                } else {
                    memo2[j] = Math.max(memo1[j], memo1[j - weights[i]] + values[i]);
                }
            }

            int[] temp = memo1;
            memo1 = memo2;
            memo2 = temp;
        }

        return memo1[memo1.length - 1];
    }


    // time O(vW) space O(nW)
    String maximumKnapsackSolutionDp(int[] values, int[] weights, int total) {

        int[][] memo = new int[values.length][total + 1];

        for (int i = 0; i <= total; i++) {
            if (weights[0] <= i) {
                memo[0][i] = weights[0];
            }
        }

        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j - weights[i] < 0) {
                    memo[i][j] = memo[i - 1][j];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - weights[i]] + values[i]);
                }
            }
        }

        StringBuilder solution = new StringBuilder();

        int i = values.length - 1;
        int j = total;
        while (i - 1 >= 0 && j >= 0) {

            if (memo[i][j] == memo[i - 1][j]) {
                i = i - 1;
                if (i - 1 < 0 && memo[i][j] > 0) {
                    solution.append(i+1);
                    break;
                }
                continue;
            }

            solution.append(i+1);
            j = j - weights[i];
            i = i - 1;
        }

        return solution.toString();
    }
}