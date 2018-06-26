package com.practice.fmamic.part2;

import com.practice.fmamic.data.structure.MaxHeap;
import com.practice.fmamic.data.structure.MinHeap;

import java.util.List;

class MedianMaintenance {

    long calculate(final List<Integer> stream) {

        final MinHeap minHeap = new MinHeap(10000);
        final MaxHeap maxHeap = new MaxHeap(10000);

        long sumOfMedian = 0;
        for (int i = 0; i < stream.size(); i++) {

            int value = stream.get(i);

            if (value < maxHeap.first() || maxHeap.size() == 0) {
                maxHeap.insert(value);
            } else {
                minHeap.insert(value);
            }

            if (Math.abs(maxHeap.size() - minHeap.size()) > 1) {

                if (maxHeap.size() > minHeap.size()) {
                    minHeap.insert(maxHeap.extractMax());
                } else {
                    maxHeap.insert(minHeap.extractMin());
                }

            }

            if ((i+1) % 2 == 0) {
                sumOfMedian += maxHeap.first();
            } else {
                if (maxHeap.size() > minHeap.size()) {
                    sumOfMedian += maxHeap.first();
                } else {
                    sumOfMedian += minHeap.first();
                }
            }

        }

        return sumOfMedian;
    }

}
