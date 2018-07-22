package com.practice.fmamic.part3;

class OptimalBST {

    int calculateOptimalBSTCost(int[] frequency) {

        int[][] dp = new int[frequency.length][frequency.length];

        for (int i = 0; i < frequency.length; i++) {
            dp[i][i] = frequency[i];
        }

        for (int i = 0; i < frequency.length; i++) {
            for (int j = i + 1, p = 0; j < frequency.length; j++, p++) {

                int sum = 0;
                for (int k = p; k <= j; k++) {
                    sum += frequency[k];
                }

                int result = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    if (k - 1 < j && k + 1 <= j && p + 1 <= i) {
                        result = Math.min(result, sum + dp[p + 1][k + 1]);
                    } else if (p + 1 > i && k - 1 >= j) {
                        result = Math.min(result, sum + dp[p][k - 1]);
                    } else if (p + 1 > i && k - 1 < j || k + 1 > j) {
                        result = Math.min(result, sum);
                    } else {
                        result = Math.min(result, sum + Math.min(dp[p][k - 1], dp[p + 1][k + 1]));
                    }
                }

                dp[p][j] = result;
            }
        }

        return dp[0][frequency.length - 1];
    }

}
