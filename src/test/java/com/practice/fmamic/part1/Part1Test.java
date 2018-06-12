package com.practice.fmamic.part1;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

public class Part1Test {

    @Test
    public void maximumSubarrayNaiveTest() {
        MaximumSubarray subarray = new MaximumSubarray();

        int[] prices = new int[] {100, 113, 110, 85, 105, 102};

        assertEquals(20, subarray.maximumSubarrayNaive(prices));
    }

    @Test
    public void maximumSubarrayOptimizedTest() {
        MaximumSubarray subarray = new MaximumSubarray();

        int[] prices = new int[] {100, 113, 110, 85, 105, 102};

        assertEquals(20, subarray.maximumSubarrayOptimized(prices));
    }
}
