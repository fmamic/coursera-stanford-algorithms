package com.practice.fmamic.part2;

import com.practice.fmamic.data.structure.Edge;
import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.Vertex;

import java.util.*;

class DijkstraShortestPath {

    Map<Integer, Integer> calculate(final Graph graph) {

        final Map<Integer, Integer> result = new HashMap<>();
        final List<Vertex> vertices = graph.getVertices();

        vertices.get(0).setDistance(0);

        while (!vertices.isEmpty()) {

            Collections.sort(vertices, new Comparator<Vertex>() {
                @Override
                public int compare(final Vertex o1, final Vertex o2) {
                    return o1.getDistance().compareTo(o2.getDistance());
                }
            });

            Vertex vertex = vertices.get(0);
            vertices.remove(vertex);

            for (final Edge edge : graph.getEdges()) {
                if (edge.getSource().equals(vertex)) {
                    final Integer potential = vertex.getDistance() + edge.getWeight();
                    final Integer current = edge.getDestination().getDistance();

                    if (potential < current) {
                        edge.getDestination().setDistance(potential);
                    }
                }
            }
            result.put(vertex.getValue(), vertex.getDistance());
        }

        return result;
    }

}
