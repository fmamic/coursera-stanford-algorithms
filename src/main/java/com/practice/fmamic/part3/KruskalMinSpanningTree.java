package com.practice.fmamic.part3;

import com.practice.fmamic.data.structure.Edge;
import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.Vertex;

import java.util.*;

public class KruskalMinSpanningTree {

    public long calculateMSTNaive(final Graph graph) {

        List<Edge> edges = graph.getEdges();

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(final Edge o1, final Edge o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        final Graph mstGraph = new Graph();

        long total = 0;
        for (final Edge edge : edges) {

            boolean isSource = false;
            boolean isDest = false;
            Vertex source;
            Vertex destination;

            if (mstGraph.getVertices().contains(edge.getSource())) {
                source = mstGraph.getVertex(edge.getSource().getValue());
                isSource = true;
            } else {
                source = new Vertex(edge.getSource().getValue());
                mstGraph.addVertex(source);
            }

            if (mstGraph.getVertices().contains(edge.getDestination())) {
                destination = mstGraph.getVertex(edge.getDestination().getValue());
                isDest = true;
            } else {
                destination = new Vertex(edge.getDestination().getValue());
                mstGraph.addVertex(destination);
            }

            Edge created = mstGraph.addEdge(source, destination);
            if (hasCycle(source, new ArrayList<>(), null)) {
                mstGraph.removeEdge(source, destination, created);

                if (!isSource) {
                    mstGraph.removeVertex(source);
                }

                if (!isDest) {
                    mstGraph.removeVertex(destination);
                }

            } else {
                total += edge.getWeight();
            }
        }

        return total;
    }

    private boolean hasCycle(final Vertex vertex, final List<Vertex> visited, final Vertex parent) {

        visited.add(vertex);

        for (final Vertex adjacent : vertex.getAdjacencyList()) {
            if (!visited.contains(adjacent)) {
                if (hasCycle(adjacent, visited, vertex)) {
                    return true;
                }
            } else if (parent != null && parent != adjacent) {
                return true;
            }
        }

        return false;
    }


    public void calculateMSTUnionFind() {

    }

}
