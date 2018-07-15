package com.practice.fmamic.part3;

import com.practice.fmamic.data.structure.*;

import java.util.*;

class Clustering {

    long maxClusterDistance(final Graph graph, int clusterSize) {

        final DisjointSetNaive disjointSet = new DisjointSetNaive(graph.getVertices().size());
        final List<Edge> edges = graph.getEdges();

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(final Edge o1, final Edge o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        for (final Edge edge : edges) {

            if (disjointSet.getSize() == clusterSize)
                break;

            disjointSet.union(edge.getSource().getValue(), edge.getDestination().getValue());
        }

        long result = Long.MAX_VALUE;

        for (final Edge edge : edges) {
            if (disjointSet.find(edge.getSource().getValue()) != disjointSet.find(edge.getDestination().getValue())) {
                result = Math.min(result, edge.getWeight());
            }
        }

        return result;
    }

    int hammingClusterDistance(final Map<Integer, Vertex> input) {
        final Graph graph = new Graph();
        for (Map.Entry<Integer, Vertex> entry : input.entrySet()) {
            graph.addVertex(entry.getValue());
            for (int i = 0; i < 24; i++) {

                int flipped1 = flipBit(entry.getKey(), i);
                Vertex inputKey1 = input.get(flipped1);
                if (inputKey1 != null) {
                    graph.addEdge(entry.getValue(), inputKey1);
                }

                for (int j = i+1; j < 24; j++) {
                    int flipped = flipBit(flipBit(entry.getKey(), j), i);
                    Vertex inputKey = input.get(flipped);
                    if (inputKey != null) {
                        graph.addEdge(entry.getValue(), inputKey);
                    }
                }
            }

        }

        final DisjointSet disjointSet = new DisjointSet(graph.getVertices().size());
        final List<Edge> edges = graph.getEdges();

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(final Edge o1, final Edge o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        for (final Edge edge : edges) {
            disjointSet.union(edge.getSource().getValue(), edge.getDestination().getValue());
        }

        return disjointSet.getSize();
    }

    int flipBit(int i1, int position) {
        return i1 ^ (1 << position);
    }

    int distance(int i1, int i2) {

        int value = i1 ^ i2;
        int count = 0;

        while (value != 0) {
            count += value & 1;
            value >>= 1;
        }

        return count;
    }
}
