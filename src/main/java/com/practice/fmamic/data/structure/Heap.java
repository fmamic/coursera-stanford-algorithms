package com.practice.fmamic.data.structure;

public class Heap {

    private int[] array;

    private int size = 0;

    private Integer capacity = 10 + 1;

    public Heap() {
        this.array = new int[capacity];
    }

    public Heap(final Integer capacity) {
        this.capacity = capacity + 1;
        this.array = new int[capacity];
    }

    public void insert(int value) {
        this.array[++size] = value;
        heapify(size);
    }

    public int extractMin() {
        int min = this.array[1];
        int temp = this.array[size];
        size--;

        this.array[1] = temp;

        heapifyMin(1);

        return min;
    }

    private void heapifyMin(int position) {

        int child1 = 2 * position;
        int child2 = 2 * position + 1;

        if (child1 > size && child2 > size)
            return;

        if (child2 > size || this.array[child1] < this.array[child2]) {
            int temp = this.array[position];
            this.array[position] = this.array[child1];
            this.array[child1] = temp;

            heapifyMin(child1);
        } else {
            int temp = this.array[position];
            this.array[position] = this.array[child2];
            this.array[child2] = temp;

            heapifyMin(child2);
        }
    }

    private void heapify(int position) {
        int parent = position / 2;

        if (position == parent)
            return;

        if (this.array[position] < this.array[parent]) {
            int temp = this.array[position];
            this.array[position] = this.array[parent];
            this.array[parent] = temp;

            if (position != 0)
                heapify(parent);
        }
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }
}
