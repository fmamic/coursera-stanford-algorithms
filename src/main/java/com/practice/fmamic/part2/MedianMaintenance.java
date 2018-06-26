package com.practice.fmamic.part2;

import com.practice.fmamic.data.structure.MaxHeap;
import com.practice.fmamic.data.structure.MinHeap;

import java.util.List;

class MedianMaintenance {

    int calculate(final List<Integer> stream) {

        final MinHeap minHeap = new MinHeap();
        final MaxHeap maxHeap = new MaxHeap();

        int sumOfMedian = 0;
        for (int i = 0; i < stream.size(); i++) {

            int value = stream.get(i);
            int sizeMax = maxHeap.size();
            int sizeMin = minHeap.size();

            if (value < maxHeap.first()) {
                maxHeap.insert(value);
            } else {
                minHeap.insert(value);
            }

            if (Math.abs(sizeMax - sizeMin) > 1) {

                if (maxHeap.size() > minHeap.size()) {
                    minHeap.insert(maxHeap.extractMax());
                } else {
                    maxHeap.insert(minHeap.extractMin());
                }
                
            }

            if (i % 2 == 0) {
                sumOfMedian += (maxHeap.first() + minHeap.first()) / 2;
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
