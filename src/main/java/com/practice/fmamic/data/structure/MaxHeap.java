package com.practice.fmamic.data.structure;

public class MaxHeap {

    private int size = 0;

    private int[] store;

    public MaxHeap() {
        this.store = new int[10];
    }

    public MaxHeap(final Integer initialCapacity) {
        this.store = new int[initialCapacity];
    }

    public void insert(int value) {
        this.store[++size] = value;
        minHeapify(size);
    }

    public int first() {
        return this.store[1];
    }

    public int size() {
        return size;
    }

    public int extractMax() {
        int max = this.store[1];
        this.store[1] = this.store[size--];

        maxHeapify(1);

        return max;
    }

    private void maxHeapify(final int position) {
        int value = this.store[position];
        int left = left(position);
        int right = right(position);

        int largest = position;
        if (getLeftIndex(position) <= size && value < left) {
            largest = getLeftIndex(position);
        }

        if (getRightIndex(position) <= size && this.store[largest] < right) {
            largest = getRightIndex(position);
        }

        if (largest != position) {
            this.store[position] = this.store[largest];
            this.store[largest] = value;
            maxHeapify(position);
        }
    }

    public void minHeapify(int position) {
        int value = this.store[position];
        int parent = parent(position);

        if (position > 1 && value > parent) {
            this.store[position] = parent;
            this.store[getParentIndex(position)] = value;

            minHeapify(getParentIndex(position));
        }
    }

    private int left(int position) {
        return this.store[getLeftIndex(position)];
    }

    private int getLeftIndex(final int position) {
        return 2 * position;
    }

    private int right(int position) {
        return this.store[getRightIndex(position)];
    }

    private int getRightIndex(final int position) {
        return 2 * position + 1;
    }

    private int parent(int position) {
        return this.store[getParentIndex(position)];
    }

    private int getParentIndex(final int position) {
        return position / 2;
    }
}
