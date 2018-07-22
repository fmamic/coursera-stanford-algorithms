package com.practice.fmamic.part3;

class OptimalBST {

    int calculateOptimalBSTCost(int[] frequency) {

        int[][] dp = new int[frequency.length][frequency.length];

        for (int i = 0; i < frequency.length; i++) {
            dp[i][i] = frequency[i];
        }

        for (int length = 2; length <= frequency.length; length++) {

            for (int i = 0; i <= frequency.length - length; i++) {

                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;

                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += frequency[k];
                }

                for (int r = i; r <= j; r++) {
                    int cost = ((r > i) ? dp[i][r-1] : 0) + ((r < j) ? dp[r+1][j] : 0) + sum;

                    if (cost < dp[i][j])
                        dp[i][j] = cost;
                }
            }
        }

        return dp[0][frequency.length - 1];
    }

}
