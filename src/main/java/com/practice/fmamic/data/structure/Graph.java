package com.practice.fmamic.data.structure;

import java.util.*;

// Undirected Graph with unique Vertex values
public class Graph {

    private Map<Integer, Vertex> vertices = new TreeMap<>();
    private List<Edge> edges = new ArrayList<>();

    public List<Edge> getEdges() {
        return this.edges;
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(this.vertices.values());
    }

    public void addVertex(final Vertex vertex) {
        vertices.put(vertex.getValue(), vertex);
    }

    public Edge addEdge(final Vertex vertex1, final Vertex vertex2) {
        vertex1.getAdjacencyList().add(vertex2);
        vertex2.getAdjacencyList().add(vertex1);

        Edge edge = new Edge(vertex1, vertex2);
        edges.add(edge);
        return edge;
    }

    public void removeVertex(final Vertex vertex) {
        vertices.remove(vertex.getValue());
    }

    public void removeEdge(final Vertex vertex1, final Vertex vertex2, final Edge edge) {
        vertex1.getAdjacencyList().remove(vertex2);
        vertex2.getAdjacencyList().remove(vertex1);

        edges.remove(edge);
    }


    public void addEdge(final Vertex vertex1, final Vertex vertex2, final Integer weight) {
        vertex1.getAdjacencyList().add(vertex2);
        vertex2.getAdjacencyList().add(vertex1);

        edges.add(new Edge(vertex1, vertex2, weight));
    }

    public void addEdgeDirected(final Vertex vertex1, final Vertex vertex2) {
        vertex1.getAdjacencyList().add(vertex2);
        edges.add(new Edge(vertex1, vertex2));
    }

    public boolean containsEdge(final Vertex v1, final Vertex v2) {

        for (final Edge edge : edges) {

            if (edge.getSource().getValue().equals(v1.getValue()) && edge.getDestination().getValue().equals(v2.getValue())) {
                return true;
            }

            if (edge.getSource().getValue().equals(v2.getValue()) && edge.getDestination().getValue().equals(v1.getValue())) {
                return true;
            }
        }

        return false;
    }

    public boolean contains(final Integer vertex) {
        return vertices.containsKey(vertex);
    }

    public Vertex getVertex(final Integer value) {
        return vertices.get(value);
    }

}
