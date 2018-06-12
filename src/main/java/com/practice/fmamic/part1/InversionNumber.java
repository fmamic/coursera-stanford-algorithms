package com.practice.fmamic.part1;

public class InversionNumber {

    long countInversionNumberNaive(int[] input) {
        int result = 0;
        return 100L;
    }

    long countInversionNumberOptimized(int[] input) {
        return countInversionNumberOptimized(input, 0, input.length);
    }

    long countInversionNumberOptimized(int[] input, int i, int j) {

        if (i == j)
            return 0L;
        
        int mid = (i + j) / 2;

        return countInversionNumberOptimized(input, i, mid) +
        countInversionNumberOptimized(input, mid+1, j) +
        countInversionNumberOptimizedSplit(input, i, mid, j);

    }

    private long countInversionNumberOptimizedSplit(int[] input, int i, int mid, int j) {



    }


}
