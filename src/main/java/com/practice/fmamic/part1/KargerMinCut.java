package com.practice.fmamic.part1;

import java.util.*;

import com.practice.fmamic.data.structure.Graph;

class KargerMinCut {

    int minCutNumber(final Graph graph) {

        final List<Graph.Edge> edges = graph.getEdges();

        int graphSize = graph.getVertices().size();

        while (graphSize > 2) {

            final Random rand = new Random();
            final int position = rand.nextInt(edges.size());

            final Graph.Edge edge = edges.get(position);
            edges.remove(edge);

            for (Graph.Edge edge1 : edges) {

                if (edge.getDestination().getValue().equals(edge1.getSource().getValue())) {
                    edge1.setSource(edge.getSource());
                }

                if (edge.getDestination().getValue().equals(edge1.getDestination().getValue())) {
                    edge1.setDestination(edge.getSource());
                }
            }

            Iterator<Graph.Edge> iterator = edges.iterator();

            //remove self pointing edges
            while (iterator.hasNext()) {
                final Graph.Edge edge1 = iterator.next();
                if (edge1.getDestination().equals(edge1.getSource()))
                    iterator.remove();
            }

            graphSize--;
        }

        return edges.size();
    }

}
