package com.practice.fmamic.part1;

import java.util.HashSet;
import java.util.Set;

import com.practice.fmamic.data.structure.Graph;

class KosarajuSCC {

    Integer finishTime = 1;

    int calculate(final Graph graph) {

        final Graph reverseGraph = reverseGraph(graph);


        Set<Graph.Vertex> visited = new HashSet<>();
        for (int i = reverseGraph.getVertices().size(); i >= 0; i--) {
            final Graph.Vertex vertex = reverseGraph.getVertex(i);

            if (!visited.contains(vertex))
                dfs(vertex, visited);
        }

        for (Graph.Vertex vertex : graph.getVertices()) {
            for (Graph.Vertex vertex1 : reverseGraph.getVertices()) {
                if (vertex.getValue().equals(vertex1.getValue())) {
                    vertex.setValue(vertex1.getValue());
                }
            }
        }

        int total = 0;
        visited = new HashSet<>();
        for (int i = 0; i < graph.getVertices().size(); i++) {
            final Graph.Vertex vertex = graph.getVertex(i);

            if (!visited.contains(vertex)) {
                dfs(vertex, visited);
                total++;
            }
        }

        return total;
    }

    private void dfs(final Graph.Vertex vertex, Set<Graph.Vertex> visited) {

        if (vertex == null) {
            return;
        }

        visited.add(vertex);

        for (final Graph.Vertex adjacent : vertex.getAdjacencyList()) {
            if (!visited.contains(adjacent)) {
                dfs(adjacent, visited);
            }
        }

        finishTime++;
        vertex.setFinish(finishTime);
    }

    private Graph reverseGraph(final Graph graph) {
        final Graph reversedGraph = new Graph();
        reversedGraph.setVertices(new HashSet<>(graph.getVertices()));

        for (final Graph.Edge edge : graph.getEdges()) {
            reversedGraph.addEdge(reversedGraph.getVertex(edge.getDestination().getValue()), reversedGraph.getVertex(edge.getSource().getValue()));
        }

        return reversedGraph;
    }

}
