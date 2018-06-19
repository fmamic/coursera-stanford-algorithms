package com.practice.fmamic.data.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Undirected Graph with unique Vertex values
public class Graph {

    private Set<Vertex> vertices = new HashSet<>();
    private List<Edge> edges = new ArrayList<>();

    public List<Edge> getEdges() {
        return this.edges;
    }

    public Set<Vertex> getVertices() {
        return this.vertices;
    }

    public void addVertex(final Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(final Vertex vertex1, final Vertex vertex2) {
        vertex1.adjacencyList.add(vertex2);
        vertex2.adjacencyList.add(vertex1);

        edges.add(new Edge(vertex1, vertex2));
    }

    public boolean contains(final Integer vertex) {
        for (final Vertex vertex1 : vertices) {
            if (vertex1.getValue().equals(vertex))
                return true;
        }
        return false;
    }

    public Vertex getVertex(final Integer value) {
        for (final Vertex vertex : vertices) {
            if (vertex.value.equals(value))
                return vertex;
        }
        return new Vertex(value);
    }

    public static class Edge {

        private Vertex source;

        private Vertex destination;

        public Edge(final Vertex source, final Vertex destination) {
            this.source = source;
            this.destination = destination;
        }

        public Vertex getSource() {
            return source;
        }

        public Vertex getDestination() {
            return destination;
        }

        public void setSource(final Vertex source) {
            this.source = source;
        }

        public void setDestination(final Vertex destination) {
            this.destination = destination;
        }
    }

    public static class Vertex {

        private Integer value;

        private List<Vertex> adjacencyList = new ArrayList<>();

        public Vertex(final Integer value) {
            this.value = value;
        }

        public List<Vertex> getAdjacencyList() {
            return adjacencyList;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Vertex vertex = (Vertex) o;

            return value != null ? value.equals(vertex.value) : vertex.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }
}
