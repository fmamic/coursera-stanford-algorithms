package com.practice.fmamic.part2;

import com.practice.fmamic.data.structure.Graph;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class Part2Test {

    @Test
    public void kosarajuTest() {
        final Graph graph = new Graph();

        Graph.Vertex v1 = new Graph.Vertex(1);
        Graph.Vertex v2 = new Graph.Vertex(2);
        Graph.Vertex v3 = new Graph.Vertex(3);
        Graph.Vertex v4 = new Graph.Vertex(4);
        Graph.Vertex v5 = new Graph.Vertex(5);
        Graph.Vertex v6 = new Graph.Vertex(6);
        Graph.Vertex v7 = new Graph.Vertex(7);
        Graph.Vertex v8 = new Graph.Vertex(8);
        Graph.Vertex v9 = new Graph.Vertex(9);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);

        graph.addEdgeDirected(v4, v1);
        graph.addEdgeDirected(v1, v7);
        graph.addEdgeDirected(v7, v4);
        graph.addEdgeDirected(v7, v9);
        graph.addEdgeDirected(v9, v6);
        graph.addEdgeDirected(v3, v9);
        graph.addEdgeDirected(v6, v8);
        graph.addEdgeDirected(v6, v3);
        graph.addEdgeDirected(v8, v2);
        graph.addEdgeDirected(v2, v5);
        graph.addEdgeDirected(v5, v8);

        KosarajuSCC kosarajuSCC = new KosarajuSCC();
        assertEquals(3, kosarajuSCC.calculate(graph));
    }

    @Test
    public void kosarajuTest2() {
        final Graph  graph = getDirectedGraphData("scc.txt");
        KosarajuSCC kosarajuSCC = new KosarajuSCC();
        assertEquals(371762, kosarajuSCC.calculate(graph));
    }

    private Graph getDirectedGraphData(final String s) {
        File file = new File(getClass().getClassLoader().getResource(s).getFile());

        final Graph graph = new Graph();
        final Map<Integer, Graph.Vertex> map = new HashMap<>();


        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                String[] vertices = line.split(" ");

                final Graph.Vertex vertexSource;
                final Graph.Vertex vertexDestination;

                final Integer destination = Integer.valueOf(vertices[0]);
                final Integer source = Integer.valueOf(vertices[1]);

                if (!map.containsKey(source)) {
                    vertexSource = new Graph.Vertex(source);
                    graph.addVertex(vertexSource);
                    map.put(source, vertexSource);
                } else {
                    vertexSource = map.get(source);
                }

                if (!map.containsKey(destination)) {
                    vertexDestination = new Graph.Vertex(destination);
                    graph.addVertex(vertexDestination);
                    map.put(destination, vertexDestination);
                } else {
                    vertexDestination = map.get(destination);
                }

                graph.addEdgeDirected(vertexDestination, vertexSource);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }
}
