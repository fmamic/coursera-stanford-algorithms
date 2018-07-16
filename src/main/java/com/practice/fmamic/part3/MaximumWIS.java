package com.practice.fmamic.part3;

class MaximumWIS {

    long calculateMaximumWIS(final long[] input) {

        if (input.length < 2)
            return input[0];

        long[] dp = new long[input.length + 1];
        dp[0] = 0;
        dp[1] = input[0];

        for (int i = 1; i < input.length; i++) {
            dp[i+1] = input[i] + dp[i-1];
        }

        return dp[dp.length - 1] > dp[dp.length - 2] ? dp[dp.length - 1] : dp[dp.length - 2];
    }

}
