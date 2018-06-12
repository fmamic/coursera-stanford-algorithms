package com.practice.fmamic.part1;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @Test
    public void inversionTest1() {
        InversionNumber inversionNumber = new InversionNumber();

        File file = new File(getClass().getClassLoader().getResource("inversionTestFile.txt").getFile());

        List<Integer> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(Integer.parseInt(line));
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] input = new int[list.size()];

        int number = 0;
        for (Integer i : list) {
            input[number++] = i;
        }

        inversionNumber.countInversionNumberOptimized(input);
    }
}
