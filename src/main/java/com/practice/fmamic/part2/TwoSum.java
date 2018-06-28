package com.practice.fmamic.part2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class TwoSum {

    int calculate(final long[] input, final long minRange, final long maxRange) {

        Arrays.sort(input);

        int target = 0;
        Set<Long> sum = new HashSet<>();

        for (int i = 0; i < input.length; i++) {

            long min = minRange - input[i];
            long max = maxRange - input[i];

            int indexMin = Arrays.binarySearch(input, min);
            int indexMax = Arrays.binarySearch(input, max);

            if (indexMin < 0) {
                indexMin = -indexMin - 1;
            }

            if (indexMax < 0) {
                indexMax = -indexMax - 1;

                if (indexMax < input.length && input[indexMax] != max) {
                    indexMax--;
                }
            }

            if (indexMax > input.length - 1 && indexMin > input.length - 1)
                continue;

            if (indexMax > input.length - 1)
                indexMax = input.length - 1;

            if (indexMin > input.length - 1)
                indexMin = input.length - 1;

            for (int k = indexMin; k <= indexMax; k++) {

                Long value = input[k] + input[i];

                if (!sum.contains(value) && input[k] != input[i]) {
                    target++;
                    sum.add(value);
                }
            }
        }

        return target;
    }
}