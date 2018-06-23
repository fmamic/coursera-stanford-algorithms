package com.practice.fmamic.part2;

import java.util.*;

import com.practice.fmamic.data.structure.Graph;

class KosarajuSCC {

    Integer finishTime = 1;

    int calculate(final Graph reverseGraph) {

        Set<Graph.Vertex> visited = new HashSet<>();
        for (int i = reverseGraph.getVertices().size(); i > 0; i--) {
            final Graph.Vertex vertex = reverseGraph.getVertex(i);

            if (!visited.contains(vertex))
                dfs(vertex, visited);
        }

        final Graph graphUpdated = new Graph();

        for (final Graph.Vertex vertex : reverseGraph.getVertices()) {
            graphUpdated.addVertex(new Graph.Vertex(vertex.getFinish()));
        }

        for (final Graph.Edge edge : reverseGraph.getEdges()) {
            final Graph.Vertex source = graphUpdated.getVertex(edge.getSource().getFinish());
            final Graph.Vertex destination = graphUpdated.getVertex(edge.getDestination().getFinish());

            graphUpdated.addEdgeDirected(destination, source);
        }

        int total = 0;
        visited = new HashSet<>();
        List<Integer> largest = new ArrayList<>();
        int last = 0;
        for (int i = graphUpdated.getVertices().size(); i > 0; i--) {
            final Graph.Vertex vertex = graphUpdated.getVertex(i);

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

        vertex.setFinish(finishTime);
        finishTime++;
    }

    private Graph reverseGraph(final Graph graph) {
        final Graph reversedGraph = new Graph();

        for (final Graph.Vertex vertex : graph.getVertices()) {
            reversedGraph.getVertices().add(new Graph.Vertex(vertex.getValue()));
        }


        for (final Graph.Edge edge : graph.getEdges()) {
            reversedGraph.addEdgeDirected(reversedGraph.getVertex(edge.getDestination().getValue()), reversedGraph.getVertex(edge.getSource().getValue()));
        }

        return reversedGraph;
    }

}
