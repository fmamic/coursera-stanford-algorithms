package com.practice.fmamic.part1;

/**
 * Suppose that you been offered the opportunity to invest in the Volatile Chemical
 * Corporation. Like the chemicals the company produces, the stock price of the
 * Volatile Chemical Corporation is rather volatile. You are allowed to buy one unit
 * of stock only one time and then sell it at a later date, buying and selling after the
 * close of trading for the day. To compensate for this restriction, you are allowed to
 * learn what the price of the stock will be in the future. Your goal is to maximize
 * your profit.
 *
 * Input: array of prices per day (index is indicating day number)
 * Output: maximum sum
 *
 * Naive & Optimized solution required.
 *
 */
class MaximumSubarray {

    // Running time: O(n^2), space: O(1)
    int maximumSubarrayNaive(int[] prices) {

        int sum = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                int subSum = prices[j] - prices[i];

                if (subSum > sum) {
                    sum = subSum;
                }
            }
        }

        return sum;
    }

    // Divide and Conquer, Running time: T(n) = 2T(n/2) + O(n) => O(nLogN), space O(n)
    int maximumSubarrayOptimized(int[] prices) {
        int[] changes = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {
            changes[i] = prices[i] - prices[i-1];
        }

        return maximumSubarrayOptimized(changes, 0, prices.length-1);
    }

    private int maximumSubarrayOptimized(int[] changes, int i, int j) {

        if (i == j)
            return 0;

        int mid = (j + i) / 2; // check for overflow

        int subSum1 = maximumSubarrayOptimized(changes, i, mid);
        int subSum2 = maximumSubarrayOptimized(changes, mid+1, j);
        int subSum3 = maximumSubarraySplitCheck(changes, i, mid, j);

        return Math.max(subSum1, Math.max(subSum2, subSum3));
    }

    private int maximumSubarraySplitCheck(final int[] changes, final int i, final int mid, final int j) {

        int subSum1 = 0;
        int subSum2 = 0;
        int max = 0;

        for (int k = mid-1; k >= i; k--) {
            subSum1 += changes[k];

            if (subSum1 > max)
                max = subSum1;
        }

        for (int k = mid+1; k <= j; k++) {
            subSum2 += changes[k];

            if (subSum2 > max)
                max = subSum2;
        }

        return max;
    }
}
