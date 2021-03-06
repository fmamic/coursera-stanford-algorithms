package com.practice.fmamic.part2;

import static junit.framework.TestCase.*;

import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.MaxHeap;
import com.practice.fmamic.data.structure.MinHeap;
import com.practice.fmamic.data.structure.Vertex;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Part2Test {

    @Test
    public void kosarajuTest() {
        final Graph graph = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);
        Vertex v9 = new Vertex(9);

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

    @Test
    public void dijkstraTest1() {
        final Graph graph = getDijkstraGraphData("dijkstra.txt");
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath();

        assertNotNull(dijkstraShortestPath.calculate(graph));
    }

    @Test
    public void dijkstraTest2() {

        final Graph graph = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(v1, v2, 1);
        graph.addEdge(v2, v4, 6);
        graph.addEdge(v2, v3, 2);
        graph.addEdge(v1, v3, 4);
        graph.addEdge(v3, v4, 3);

        final DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath();

        assertNotNull(dijkstraShortestPath.calculate(graph));
    }

    @Test
    public void dijkstraTest3() {

        final Graph graph = new Graph();

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);

        graph.addEdge(v0, v1, 4);
        graph.addEdge(v0, v7, 8);
        graph.addEdge(v1, v7, 11);
        graph.addEdge(v1, v2, 8);
        graph.addEdge(v2, v8, 2);
        graph.addEdge(v2, v3, 7);
        graph.addEdge(v7, v8, 7);
        graph.addEdge(v7, v6, 1);
        graph.addEdge(v6, v5, 2);
        graph.addEdge(v2, v5, 4);
        graph.addEdge(v8, v6, 6);
        graph.addEdge(v3, v5, 14);
        graph.addEdge(v3, v4, 9);
        graph.addEdge(v5, v4, 10);

        final DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath();

        assertNotNull(dijkstraShortestPath.calculate(graph));
    }

    @Test
    public void minHeapTest() {
        MinHeap heap = new MinHeap();

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);

        assertEquals(1, heap.extractMin());
        assertEquals(2, heap.extractMin());

        heap.insert(1);

        assertEquals(1, heap.extractMin());
        assertEquals(3, heap.extractMin());

        heap.insert(13);

        assertEquals(4, heap.extractMin());
        assertEquals(13, heap.extractMin());
    }

    @Test
    public void maxHeapTest() {
        final MaxHeap heap = new MaxHeap();

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);

        assertEquals(4, heap.extractMax());
        assertEquals(3, heap.extractMax());
        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());

        heap.insert(1);
        heap.insert(2);

        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());

        heap.insert(100);
        heap.insert(1002);

        assertEquals(1002, heap.extractMax());
        assertEquals(100, heap.extractMax());

        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);

        assertEquals(4, heap.extractMax());
        assertEquals(3, heap.extractMax());

        heap.insert(5);

        assertEquals(5, heap.extractMax());
        assertEquals(2, heap.extractMax());

    }

    @Test
    public void medianTest() {
        final List<Integer> stream = getMedianStream("median.txt");
        final MedianMaintenance medianMaintenance = new MedianMaintenance();

        long result = medianMaintenance.calculate(stream);

        assertEquals(1213, result % 10000);
    }

    @Test
    public void twoSumTest7() {
        TwoSum twoSum = new TwoSum();
        assertEquals(8, twoSum.calculate(new long[] {-3,-1,1,2,9,11,7,6,2}, 3, 10));
    }

    @Test
    public void twoSumTest() {
        TwoSum twoSum = new TwoSum();
        assertEquals(427, twoSum.calculate(getTwoSumData("2sum.txt"), -10000, 10000));
    }

    private long[] getTwoSumData(final String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        final long[] stream = new long[1000000];

        try (final Scanner scanner = new Scanner(file)) {

            int i = 0;
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                stream[i++] = Long.parseLong(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }

    private List<Integer> getMedianStream(final String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        final List<Integer> stream = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                stream.add(Integer.parseInt(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }

    private Graph getDijkstraGraphData(final String s) {
        File file = new File(getClass().getClassLoader().getResource(s).getFile());

        final Graph graph = new Graph();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();

                String[] split1 = line.split("\t");

                final Vertex source;

                if (!graph.contains(Integer.valueOf(split1[0]))) {
                    source = new Vertex(Integer.valueOf(split1[0]));
                } else {
                    source = graph.getVertex(Integer.valueOf(split1[0]));
                }

                graph.addVertex(source);

                for (int i = 1; i < split1.length; i++) {
                    String[] edgeData = split1[i].split(",");

                    Integer weight = Integer.valueOf(edgeData[1]);
                    Integer key = Integer.valueOf(edgeData[0]);

                    Vertex destination;

                    if (!graph.contains(key)) {
                        destination = new Vertex(key);
                    } else {
                        destination = graph.getVertex(key);
                    }

                    graph.addVertex(destination);

                    graph.addEdge(source, destination, weight);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private Graph getDirectedGraphData(final String s) {
        File file = new File(getClass().getClassLoader().getResource(s).getFile());

        final Graph graph = new Graph();
        final Map<Integer, Vertex> map = new HashMap<>();


        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                String[] vertices = line.split(" ");

                final Vertex vertexSource;
                final Vertex vertexDestination;

                final Integer destination = Integer.valueOf(vertices[0]);
                final Integer source = Integer.valueOf(vertices[1]);

                if (!map.containsKey(source)) {
                    vertexSource = new Vertex(source);
                    graph.addVertex(vertexSource);
                    map.put(source, vertexSource);
                } else {
                    vertexSource = map.get(source);
                }

                if (!map.containsKey(destination)) {
                    vertexDestination = new Vertex(destination);
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
