package com.practice.fmamic.part4;

public class Graph {

    class Edge {
        int source;
        int destination;
        int weight;
        Edge() {
            source = destination = weight = 0;
        }
    }

    Edge[] edge;
    private int vertexSize;
    private int edgeSize;

    Graph (int vertexSize, int edgeSize) {
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