package com.practice.fmamic.part4;

import java.util.Arrays;

import com.practice.fmamic.part4.data.Graph;

class BellmanFord {

    // O(n^2) space O(n*m) time
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

                if (dp[i - 1][u] != Integer.MAX_VALUE)
                    dp[i][v] = Math.min(dp[i][v], weight + dp[i][u]);
            }
        }

        return dp[graph.getVertexSize() - 1][graph.getVertexSize() - 1];
    }

    // O(n) space, O(n*m) time
    String calculateDistanceSpaceOptimizationWithPath(Graph graph) {

        int[] path = new int[graph.getVertexSize()];
        int[] dp = new int[graph.getVertexSize()];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        path[0] = 0;

        // O(m*n)
        for (int i = 1; i < graph.getVertexSize(); i++) {
            for (int j = 0; j < graph.getEdgeSize(); j++) {
                int u = graph.edge[j].source;
                int v = graph.edge[j].destination;
                int weight = graph.edge[j].weight;

                if (dp[u] != Integer.MAX_VALUE && dp[v] > weight + dp[u]) {
                    dp[v] = weight + dp[u];
                    path[v] = u;
                }

            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        int index = path[path.length-1];
        stringBuilder.append(path.length-1).append("-");

        while (index != 0) {
            stringBuilder.append(index).append("-");
            index = path[index];
        }
        stringBuilder.append(index);

        return stringBuilder.toString();
    }

    // O(2^n) time
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