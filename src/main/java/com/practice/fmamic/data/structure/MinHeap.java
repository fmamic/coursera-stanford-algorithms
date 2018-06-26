package com.practice.fmamic.data.structure;

public class MinHeap {

    private int[] array;

    private int size = 0;

    private Integer capacity = 10 + 1;

    public MinHeap() {
        this.array = new int[capacity];
    }

    public MinHeap(final Integer capacity) {
        this.capacity = capacity + 1;
        this.array = new int[capacity];
    }

    public void insert(int value) {
        this.array[++size] = value;
        heapifyMax(size);
    }

    public int size() {
        return this.size;
    }

    public int first() {
        return this.array[1];
    }

    public int extractMin() {
        int min = this.array[1];
        int temp = this.array[size];
        size--;

        this.array[1] = temp;

        heapifyMin(1);

        return min;
    }

    // push down O(LogN)
    private void heapifyMin(int position) {
        int child1 = 2 * position;
        int child2 = 2 * position + 1;

        if (child1 > size && child2 > size)
            return;

        if (child2 > size || leftChild(position) < rightChild(position)) {
            int temp = this.array[position];
            this.array[position] = leftChild(position);
            this.array[child1] = temp;

            heapifyMin(child1);
        } else {
            int temp = this.array[position];
            this.array[position] = rightChild(position);
            this.array[child2] = temp;

            heapifyMin(child2);
        }
    }

    // push up O(LogN)
    private void heapifyMax(int position) {
        int parent = position / 2;

        if (position == parent)
            return;

        if (this.array[position] < parent(position)) {
            int temp = this.array[position];
            this.array[position] = parent(position);
            this.array[parent] = temp;

            if (position != 0)
                heapifyMax(parent);
        }
    }

    private int parent(int position) {
        return this.array[position / 2];
    }

    private int rightChild(int position) {
        return this.array[2 * position + 1];
    }

    private int leftChild(int position) {
        return this.array[2 * position];
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }
}
