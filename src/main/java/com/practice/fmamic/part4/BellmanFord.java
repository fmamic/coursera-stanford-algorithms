package com.practice.fmamic.part4;

import java.util.Arrays;

class BellmanFord {

    int calculateDistance(Graph graph) {

        int[][] dp = new int[graph.getVertexSize()][graph.getVertexSize()];

        for (int i = 0; i < graph.getVertexSize(); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][0] = 0;
        }

        // O(m*n)
        for (int i = 1; i < graph.getVertexSize(); i++) {
            for (int j = 0; j < graph.getEdgeSize(); j++) {
                int u = graph.edge[j].source;
                int v = graph.edge[j].destination;
                int weight = graph.edge[j].weight;

                if (dp[i-1][u] != Integer.MAX_VALUE)
                    dp[i][v] = Math.min(dp[i][v], weight + dp[i][u]);
            }
        }

        return dp[graph.getVertexSize()-1][graph.getVertexSize()-1];
    }

    int calculateDistanceRec(Graph graph, int src, int dest, int result, int edge) {

        if (edge == graph.getEdgeSize() - 1 && src != dest)
            return Integer.MAX_VALUE;

        if (src == dest)
            return result;

        int r = Integer.MAX_VALUE;

        for (int i = 0; i < graph.edge.length; i++) {
            if (graph.edge[i].source == src) {
                r = Math.min(r, calculateDistanceRec(graph, graph.edge[i].destination, dest, result + graph.edge[i].weight, edge + 1));
            }
        }

        return r;
    }

}