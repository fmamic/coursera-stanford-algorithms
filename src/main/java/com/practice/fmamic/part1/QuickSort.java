package com.practice.fmamic.part1;

class QuickSort {

    private int sum = 0;

    int sortFirstElementPivot(int[] input, int low, int high) {

        if (low >= high)
            return sum;

        int pivot = partitionFirstElementPivot(input, low, high);
        sum += high - low;
        sortFirstElementPivot(input, low, pivot - 1);
        sortFirstElementPivot(input, pivot + 1, high);

        return sum;
    }

    private int partitionFirstElementPivot(final int[] input, final int low, final int high) {

        int j = low + 1;
        for (int i = low + 1; i <= high; i++) {
            if (input[low] > input[i]) {
                int temp = input[j];
                input[j] = input[i];
                input[i] = temp;
                j++;
            }
        }

        int temp = input[j - 1];
        input[j - 1] = input[low];
        input[low] = temp;

        return j - 1;
    }

    int sortLastElementPivot(int[] input, int low, int high) {

        if (low >= high)
            return sum;

        int temp = input[low];
        input[low] = input[high];
        input[high] = temp;

        int pivot = partitionFirstElementPivot(input, low, high);
        sum += high - low;
        sortLastElementPivot(input, low, pivot - 1);
        sortLastElementPivot(input, pivot + 1, high);

        return sum;
    }

    int sortMedianPivot(int[] input, int low, int high) {

        if (low >= high)
            return sum;

        int median = low;

        int first = input[low];
        int middle = input[(low + high) / 2];
        int last = input[high];

        if (middle > first && middle < last || middle < first && middle > last) {
            median = (low + high) / 2;
        }

        if (last > first && last < middle || last < first && last > middle) {
            median = high;
        }

        int temp = input[low];
        input[low] = input[median];
        input[median] = temp;

        int pivot = partitionFirstElementPivot(input, low, high);

        sum += high - low;

        sortMedianPivot(input, low, pivot - 1);
        sortMedianPivot(input, pivot + 1, high);

        return sum;
    }
}
