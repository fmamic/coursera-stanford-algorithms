package com.practice.fmamic.data.structure;

import java.util.Arrays;

// Naive approach with O(n) worst case but O(LogN) for the amortized time
public class DisjointSetNaive {

    private int[] storage;
    private int size;

    public DisjointSetNaive(int capacity) {
        this.storage = new int[capacity + 1];
        Arrays.fill(storage, -1);
        this.size = capacity;
    }

    public int find(int key) {
        if (this.storage[key] == -1)
            return key;
        return find(this.storage[key]);
    }

    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX != parentY) {
            this.storage[parentX] = parentY;
            this.size--;
        }
    }

    public int getSize() {
        return size;
    }

    public int[] getStorage() {
        return this.storage;
    }
}
