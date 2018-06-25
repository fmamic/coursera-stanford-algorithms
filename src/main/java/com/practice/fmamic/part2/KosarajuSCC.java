package com.practice.fmamic.part2;

import java.util.*;

import com.practice.fmamic.data.structure.Edge;
import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.Vertex;

class KosarajuSCC {

    Integer finishTime = 1;

    int calculate(final Graph reverseGraph) {

        Set<Vertex> visited = new HashSet<>();
        for (int i = reverseGraph.getVertices().size(); i > 0; i--) {
            final Vertex vertex = reverseGraph.getVertex(i);

            if (!visited.contains(vertex))
                dfs(vertex, visited);
        }

        final Graph graphUpdated = new Graph();

        for (final Vertex vertex : reverseGraph.getVertices()) {
            graphUpdated.addVertex(new Vertex(vertex.getFinish()));
        }

        for (final Edge edge : reverseGraph.getEdges()) {
            final Vertex source = graphUpdated.getVertex(edge.getSource().getFinish());
            final Vertex destination = graphUpdated.getVertex(edge.getDestination().getFinish());

            graphUpdated.addEdgeDirected(destination, source);
        }

        int total = 0;
        visited = new HashSet<>();
        List<Integer> largest = new ArrayList<>();
        int last = 0;
        for (int i = graphUpdated.getVertices().size(); i > 0; i--) {
            final Vertex vertex = graphUpdated.getVertex(i);

            if (!visited.contains(vertex)) {
                dfs(vertex, visited);
                total++;
                largest.add(Math.abs(last - visited.size()));
                last = visited.size();
            }
        }

        Collections.sort(largest, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return total;
    }

    private void dfs(final Vertex vertex, Set<Vertex> visited) {

        if (vertex == null) {
            return;
        }

        visited.add(vertex);

        for (final Vertex adjacent : vertex.getAdjacencyList()) {
            if (!visited.contains(adjacent)) {
                dfs(adjacent, visited);
            }
        }

        vertex.setFinish(finishTime);
        finishTime++;
    }

}
