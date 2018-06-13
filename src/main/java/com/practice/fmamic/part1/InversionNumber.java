package com.practice.fmamic.part1;

import java.util.Arrays;

public class InversionNumber {

    // O(n^2) time and O(1) space
    long countInversionNumberNaive(int[] input) {

        long inversions = 0;

        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {

                if (input[i] > input[j]) {
                    inversions++;
                }

            }
        }

        return inversions;
    }

    // O(nLogN) time and O(nLogN) space
    long countInversionNumberOptimized(int[] input) {
        return countInversionNumberOptimized(input, 0, input.length - 1);
    }

    long countInversionNumberOptimized(int[] input, int i, int j) {
        if (i == j)
            return 0L;

        int mid = (i + j) / 2;

        return countInversionNumberOptimized(input, i, mid) +
                countInversionNumberOptimized(input, mid + 1, j) +
                countInversionNumberOptimizedSplitMerge(input, i, mid, j);
    }

    private long countInversionNumberOptimizedSplitMerge(int[] input, int low, int mid, int high) {
        long inversions = 0;

        int[] tempLow = Arrays.copyOfRange(input, low, mid + 1);
        int[] tempHigh = Arrays.copyOfRange(input, mid + 1, high + 1);

        int i = 0;
        int j = 0;
        for (int k = low; k <= high; k++) {

            if (i == tempLow.length) {
                input[k] = tempHigh[j++];
                continue;
            }

            if (j == tempHigh.length) {
                input[k] = tempLow[i++];
                continue;
            }

            if (tempLow[i] < tempHigh[j]) {
                input[k] = tempLow[i++];
            } else {
                input[k] = tempHigh[j++];
                inversions += mid - low - i + 1;
            }

        }

        return inversions;
    }
}