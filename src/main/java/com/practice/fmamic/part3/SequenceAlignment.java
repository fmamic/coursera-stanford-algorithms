package com.practice.fmamic.part3;

class SequenceAlignment {

    // O(3^n)
    int calculateNaiveValue(String s1, String s2) {
        if ("".equals(s1) || "".equals(s2))
            return Math.max(s1.length(), s2.length());
        if (s1.charAt(0) == s2.charAt(0)) {
            return calculateNaiveValue(s1.substring(1), s2.substring(1));
        } else {
            return Math.min(calculateNaiveValue(s1.substring(1), s2), calculateNaiveValue(s1, s2.substring(1))) + 1;
        }
    }

    // O(mn) time, O(mn) space
    int calculateOptimalValue(String s1, String s2) {

        int[][] dp = calculateOptimalValues(s1, s2);

        return dp[s1.length()][s2.length()];
    }

    // O(mn) + O(m+n), O(mn) space
    String calculateOptimalSolution(String s1, String s2) {
        int[][] dp = calculateOptimalValues(s1, s2);

        StringBuilder r1 = new StringBuilder();
        StringBuilder r2 = new StringBuilder();

        int i = dp.length - 1;
        int j = dp[0].length - 1;

        while (i >= 1 && j >= 1) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                r1.append(s1.charAt(i-1));
                r2.append(s2.charAt(j-1));

                i--;
                j--;
            } else {
                if (dp[i-1][j] > dp[i][j-1]) {
                    r2.append(s2.charAt(j-1));
                    r1.append("-");
                    j--;
                } else {
                    r1.append(s1.charAt(i-1));
                    r2.append("-");
                    i--;
                }
            }
        }

        if (j > 1) {
            while (j != 1) {
                r1.append("-");
                r2.append(s2.charAt(j-1));
                j--;
            }
        }

        if (i > 1) {
            while (i != 1) {
                r2.append("-");
                r1.append(s1.charAt(i-1));
                i--;
            }
        }

        return r1.toString() + "#" + r2.toString();
    }

    private int[][] calculateOptimalValues(final String s1, final String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        dp[0][0] = 0;

        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }

            }
        }
        return dp;
    }

}
