package com.practice.fmamic.part1;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.practice.fmamic.data.structure.Graph;

class KargerMinCut {

    int minCutNumber(final Graph graph) {

        final List<Graph.Edge> edges = graph.getEdges();

        int graphSize = graph.getVertices().size();

        while (graphSize > 2) {

            final Random rand = new Random();
            final int position = rand.nextInt(graphSize) + 1;

            final Graph.Edge edge = edges.get(position);
            final Iterator<Graph.Edge> iterator = edges.iterator();

            while (iterator.hasNext()) {

                final Graph.Edge edge1 = iterator.next();

                if (edge.getDestination().getValue().equals(edge1.getSource().getValue())) {
                    edge1.setSource(edge.getSource());
                }

                if (edge.getDestination().getValue().equals(edge1.getDestination().getValue())) {
                    edge1.setDestination(edge.getSource());
                }

                if (edge1.getDestination().equals(edge1.getSource())) {
                    iterator.remove();
                }
            }

            graphSize--;
        }

        return edges.size();
    }

}
