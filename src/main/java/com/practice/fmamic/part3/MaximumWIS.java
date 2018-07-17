package com.practice.fmamic.part3;

class MaximumWIS {

    long calculateMaximumWISValue(final long[] input) {

        if (input.length < 2)
            return input[0];

        for (int i = 2; i < input.length; i++) {
            input[i] = Math.max(input[i - 1], input[i - 2] + input[i]);
        }

        return input[input.length - 1];
    }

    String calculateMaximumWISSolution(final long[] weights) {

        if (weights.length < 2)
            return "0";

        long[] result = new long[weights.length];
        result[0] = weights[0];
        result[1] = weights[1];

        for (int i = 2; i < weights.length; i++) {
            result[i] = Math.max(result[i - 1], result[i - 2] + weights[i]);
        }

        int[] solution = new int[result.length];

        for (int i = solution.length - 1; i >= 0;) {
            if ((i-1 < 0 ? 0 : result[i-1]) >= (i-2 < 0 ? 0 : result[i-2]) + weights[i]) {
                i--;
            } else {
                solution[i] = 1;
                i -= 2;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (final int aSolution : solution) {
            if (aSolution == 0)
                builder.append("0");
            else
                builder.append("1");
        }

        System.out.println(solution[0]);
        System.out.println(solution[1]);
        System.out.println(solution[2]);
        System.out.println(solution[3]);
        System.out.println(solution[16]);
        System.out.println(solution[116]);
        System.out.println(solution[516]);
        System.out.println(solution[996]);

        return builder.toString();
    }

    long calculateMaximumWISNaive(final long[] input) {
        return calculateMaximumWISNaiveR(input, 0);
    }

    private long calculateMaximumWISNaiveR(final long[] input, int position) {

        if (position >= input.length)
            return 0;

        return Math.max(input[position] + calculateMaximumWISNaiveR(input, position + 2), calculateMaximumWISNaiveR(input, position + 1));
    }

}
