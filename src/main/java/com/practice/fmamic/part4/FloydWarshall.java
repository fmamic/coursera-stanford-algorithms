package com.practice.fmamic.part4;

import java.util.Arrays;

import com.practice.fmamic.part4.data.Graph;

class FloydWarshall {

    Long calculate(final Graph graph) {

        long[][] distance = new long[graph.getVertexSize()+1][graph.getVertexSize()+1];

        for (int i = 0; i <= graph.getVertexSize(); i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
        }

        long result = Long.MAX_VALUE;

        for (int i = 0; i < graph.getEdgeSize(); i++) {
            int u = graph.edge[i].source;
            int v = graph.edge[i].destination;
            int weight = graph.edge[i].weight;
            distance[u][v] = Math.min(distance[u][v], weight);
            if (u != v)
                result = Math.min(distance[u][v], result);
        }

        for (int k = 1; k <= graph.getVertexSize(); k++) {
            for (int i = 1; i <= graph.getVertexSize(); i++) {
                for (int j = 1; j <= graph.getVertexSize(); j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE && distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        if (i != j)
                            result = Math.min(distance[i][j], result);
                    }
                }
            }
        }

        for (int i = 1; i <= graph.getVertexSize(); i++) {
            if (distance[i][i] < 0)
                return null;
        }

        return result;
    }

}
