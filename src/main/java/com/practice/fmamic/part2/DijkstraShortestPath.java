package com.practice.fmamic.part2;

import com.practice.fmamic.data.structure.Graph;

import java.util.*;

public class DijkstraShortestPath {

    public List<Integer> calculate(final Graph graph) {

        final List<Integer> result = new ArrayList<>();
        final Queue<Graph.Vertex> queue = new LinkedList<>();

        queue.offer((Graph.Vertex) graph.getVertices().toArray()[0]);

        final Map<Graph.Vertex, Integer> map = new HashMap<>();

        int i = 0;
        for (Graph.Vertex vertex : graph.getVertices()) {
            if (i == 0) {
                map.put(vertex, 0);
            } else {
                map.put(vertex, Integer.MAX_VALUE);
            }
            i++;
        }

        while (!queue.isEmpty()) {

            Graph.Vertex vertex = queue.poll();

            for (final Graph.Vertex adjacency : vertex.getAdjacencyList()) {
                queue.offer(adjacency);
            }

            for (final Graph.Edge edge : graph.getEdges()) {
                if (edge.getDestination() == vertex) {
                    final Integer potential = map.get(edge.getSource()) + edge.getWeight();
                    final Integer value = map.get(vertex);

                    if (potential < value) {
                        map.put(vertex, potential);
                    }
                }
            }
        }

        return result;
    }

}
