package com.practice.fmamic.part3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.practice.fmamic.data.structure.Edge;
import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.Vertex;

class PrimsMinSpanningTree {

    // O(V * E) without optimization
    long calculateMSTCost(final Graph graph) {

        final Set<Vertex> visited = new HashSet<>();

        visited.add(graph.getVertices().get(0));

        long sum = 0;
        while (visited.size() != graph.getVertices().size()) {

            Edge eMin = null; Vertex vMin = null;
            for (final Edge edge : graph.getEdges()) {
                if (visited.contains(edge.getSource()) && !visited.contains(edge.getDestination()) && (eMin == null || eMin.getWeight() > edge.getWeight())) {
                    vMin = edge.getDestination();
                    eMin = edge;
                } else if (!visited.contains(edge.getSource()) && visited.contains(edge.getDestination()) && (eMin == null || eMin.getWeight() > edge.getWeight())) {
                    eMin = edge;
                    vMin = edge.getSource();
                }
            }

            if (eMin != null && vMin != null) {
                visited.add(vMin);
                sum += eMin.getWeight();
            }

        }

        return sum;
    }

    // O(V * logE)
    long calculateMSTCostOptimized(final Graph graph) {

        final Set<Vertex> visited = new HashSet<>();

        visited.add(graph.getVertices().get(0));

        long sum = 0;
        while (visited.size() != graph.getVertices().size()) {

            Edge eMin = null; Vertex vMin = null;
            for (final Edge edge : graph.getEdges()) {
                if (visited.contains(edge.getSource()) && !visited.contains(edge.getDestination()) && (eMin == null || eMin.getWeight() > edge.getWeight())) {
                    vMin = edge.getDestination();
                    eMin = edge;
                } else if (!visited.contains(edge.getSource()) && visited.contains(edge.getDestination()) && (eMin == null || eMin.getWeight() > edge.getWeight())) {
                    eMin = edge;
                    vMin = edge.getSource();
                }
            }

            if (eMin != null && vMin != null) {
                visited.add(vMin);
                sum += eMin.getWeight();
            }

        }

        return sum;
    }
}
