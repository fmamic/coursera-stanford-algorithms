package com.practice.fmamic.part4.data;

public class Graph {

    public static class Edge {
        public int source;
        public int destination;
        public int weight;

        public Edge() {
            source = destination = weight = 0;
        }

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public Edge[] edge;
    private int vertexSize;
    private int edgeSize;

    public Graph (int vertexSize, int edgeSize) {
        this.vertexSize = vertexSize;
        this.edgeSize = edgeSize;
        this.edge = new Edge[edgeSize];
        for (int i = 0; i < this.edge.length; i++) {
            this.edge[i] = new Edge();
        }
    }

    public int getEdgeSize() {
        return edgeSize;
    }

    public int getVertexSize() {
        return vertexSize;
    }
}