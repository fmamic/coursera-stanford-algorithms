package com.practice.fmamic.part4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.practice.fmamic.data.structure.City;
import org.junit.Test;

public class Part4Test {

    @Test
    public void bellmanFordTest() {
        BellmanFord bellmanFord = new BellmanFord();
        Graph graph = new Graph(5, 8);

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].source = 1;
        graph.edge[2].destination = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].source = 1;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edge[4].source = 1;
        graph.edge[4].destination = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].source = 3;
        graph.edge[5].destination = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].source = 3;
        graph.edge[6].destination = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].source = 4;
        graph.edge[7].destination = 3;
        graph.edge[7].weight = -3;

        assertEquals(1, bellmanFord.calculateDistanceRec(graph, 0, 4, 0, 0));
        assertEquals(1, bellmanFord.calculateDistance(graph));
        assertEquals("4-1-0", bellmanFord.calculateDistanceSpaceOptimizationWithPath(graph));
    }

    @Test
    public void floydWarshallTest0() {
        Graph graph = new Graph(4, 6);

        graph.edge[0].source = 1;
        graph.edge[0].destination = 2;
        graph.edge[0].weight = 3;

        graph.edge[1].source = 1;
        graph.edge[1].destination = 3;
        graph.edge[1].weight = 6;

        graph.edge[2].source = 1;
        graph.edge[2].destination = 4;
        graph.edge[2].weight = 15;

        graph.edge[3].source = 3;
        graph.edge[3].destination = 4;
        graph.edge[3].weight = 2;

        graph.edge[4].source = 2;
        graph.edge[4].destination = 3;
        graph.edge[4].weight = -2;

        graph.edge[5].source = 4;
        graph.edge[5].destination = 1;
        graph.edge[5].weight = 1;

        final FloydWarshall floydWarshall = new FloydWarshall();
        assertEquals(-2L, (long) floydWarshall.calculate(graph));
    }

    @Test
    public void floydWarshallTestNegativeCycle() {
        Graph graph = new Graph(4, 6);

        graph.edge[0].source = 1;
        graph.edge[0].destination = 2;
        graph.edge[0].weight = 3;

        graph.edge[1].source = 1;
        graph.edge[1].destination = 3;
        graph.edge[1].weight = -6;

        graph.edge[2].source = 1;
        graph.edge[2].destination = 4;
        graph.edge[2].weight = 15;

        graph.edge[3].source = 3;
        graph.edge[3].destination = 4;
        graph.edge[3].weight = 2;

        graph.edge[4].source = 2;
        graph.edge[4].destination = 3;
        graph.edge[4].weight = -2;

        graph.edge[5].source = 4;
        graph.edge[5].destination = 1;
        graph.edge[5].weight = 1;

        final FloydWarshall floydWarshall = new FloydWarshall();
        assertNull(floydWarshall.calculate(graph));
    }

    @Test
    public void floydWarshallTest1() {
        final Graph graph = parseInput("apsp_1.txt");
        final FloydWarshall floydWarshall = new FloydWarshall();

        assertNull(floydWarshall.calculate(graph));
    }

    @Test
    public void floydWarshallTest2() {
        final Graph graph = parseInput("apsp_2.txt");
        final FloydWarshall floydWarshall = new FloydWarshall();

        assertNull(floydWarshall.calculate(graph));
    }

    @Test
    public void floydWarshallTest3() {
        final Graph graph = parseInput("apsp_3.txt");
        final FloydWarshall floydWarshall = new FloydWarshall();

        assertEquals(-19L, (long) floydWarshall.calculate(graph));
    }

    @Test
    public void travelingSalesmanTest1() {
        final TravelingSalesman tsp = new TravelingSalesman();
        List<City> cities = parseInputTsp("tsp.txt");
        assertEquals(1.0, tsp.tspMinimumDistanceDP(cities));
    }

    @Test
    public void travelingSalesmanTest2() {
        final TravelingSalesman tsp = new TravelingSalesman();
        List<City> cities = parseInputTsp("tsp2.txt");
        assertEquals(40.0, tsp.tspMinimumDistanceNaive(cities, cities.get(0)));
    }

    private List<City> parseInputTsp(final String fileName) {
        final File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        final List<City> cities = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String[] line = scanner.nextLine().split(" ");
                final City city = new City(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
                cities.add(city);
            }

        } catch (final IOException exception) {
            exception.printStackTrace();
        }

        return cities;
    }

    private Graph parseInput(final String fileName) {
        final File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        Graph graph = null;

        try (final Scanner scanner = new Scanner(file)) {
            final String sizeLine = scanner.nextLine();
            graph = new Graph(Integer.valueOf(sizeLine.split(" ")[0]), Integer.valueOf(sizeLine.split(" ")[1]));
            int index = 0;

            while (scanner.hasNextLine()) {
                final String[] line = scanner.nextLine().split(" ");
                graph.edge[index++] = new Graph.Edge(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2]));
            }

        } catch (final IOException exception) {
            exception.printStackTrace();
        }

        return graph;
    }

}