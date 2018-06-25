package com.practice.fmamic.part1;

import java.util.*;

import com.practice.fmamic.data.structure.Edge;
import com.practice.fmamic.data.structure.Graph;

class KargerMinCut {

    int minCutNumber(final Graph graph) {

        final List<Edge> edges = graph.getEdges();

        int graphSize = graph.getVertices().size();

        while (graphSize > 2) {

            final Random rand = new Random();
            final int position = rand.nextInt(edges.size());

            final Edge edge = edges.get(position);
            edges.remove(edge);

            for (Edge edge1 : edges) {

                if (edge.getDestination().getValue().equals(edge1.getSource().getValue())) {
                    edge1.setSource(edge.getSource());
                }

                if (edge.getDestination().getValue().equals(edge1.getDestination().getValue())) {
                    edge1.setDestination(edge.getSource());
                }
            }

            Iterator<Edge> iterator = edges.iterator();

            //remove self pointing edges
            while (iterator.hasNext()) {
                final Edge edge1 = iterator.next();
                if (edge1.getDestination().equals(edge1.getSource()))
                    iterator.remove();
            }

            graphSize--;
        }

        return edges.size();
    }

    int minCutNumberUnionFind(final Graph graph) {

        final Subset[] subsets = new Subset[graph.getVertices().size()];

        for (int i = 0; i < subsets.length; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        final List<Edge> edges = graph.getEdges();

        int vertices = graph.getVertices().size();

        while (vertices > 2) {

            final Random rand = new Random();
            final int position = rand.nextInt(edges.size());

            final Edge edge = edges.get(position);

            int subset1 = find(subsets, edge.getSource().getValue() - 1);
            int subset2 = find(subsets, edge.getDestination().getValue() - 1);

            if (subset1 == subset2)
                continue;


            union(subsets, subset1, subset2);
            vertices--;
        }

        int result = 0;

        for (Edge edge : graph.getEdges()) {
            int subset1 = find(subsets, edge.getDestination().getValue() - 1);
            int subset2 = find(subsets, edge.getSource().getValue() - 1);

            if (subset1 != subset2)
                result++;
        }

        return result;
    }

    private int find(Subset[] subset, int vertex) {

        if (subset[vertex].parent != vertex)
            subset[vertex].parent = find(subset, subset[vertex].parent);

        return subset[vertex].parent;
    }

    private void union(Subset[] subset, int subset1, int subset2) {

        int s1 = find(subset, subset1);
        int s2  = find(subset, subset2);

        if (subset[s1].rank < subset[s2].rank) {
            subset[s1].parent = s2;
        } else if (subset[s1].rank > subset[s2].rank) {
            subset[s2].parent = s1;
        } else {
            subset[s2].parent = s1;
            subset[s1].rank++;
        }
    }

    class Subset {

        int parent;

        int rank;

    }

}
