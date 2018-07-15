package com.practice.fmamic.data.structure;

public class DisjointSet {

    private Subset[] subsets;

    private int size;

    public DisjointSet(int capacity) {
        this.subsets = new Subset[capacity];
        this.size = capacity;
    }

    public void union(int key1, int key2) {
        int parent1 = find(key1);
        int parent2 = find(key2);

        if (parent1 != -1 && parent2 != -1 && parent1 == parent2)
            return;

        if (this.subsets[parent1].rank < this.subsets[parent2].rank) {
            this.subsets[parent1].parent = parent2;
        } else if (this.subsets[parent1].rank > this.subsets[parent2].rank) {
            this.subsets[parent2].parent = parent1;
        } else {
            this.subsets[parent2].parent = parent1;
            this.subsets[parent1].rank++;
        }

        this.size--;
    }

    public int getSize() {
        return this.size;
    }

    public int find(int key) {
        if (this.subsets[key] == null)
            this.subsets[key] = new Subset();

        if (this.subsets[key].parent == -1)
            return key;

        this.subsets[key].parent = find(this.subsets[key].parent);
        return this.subsets[key].parent;
    }

    static class Subset {
        int rank = 0;
        int parent = -1;
    }
}
