package com.practice.fmamic.part4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
    public void travelingSalesmanTest5() {
        final TravelingSalesman tsp = new TravelingSalesman();
        List<City> cities = parseInputTsp("tsp4.txt");
        assertEquals(14409.202165641733, tsp.tspMinimumDistanceDP(generateDistanceMatrix(cities)));
    }

    @Test
    public void travelingSalesmanTest6() {
        final TravelingSalesman tsp = new TravelingSalesman();
        List<City> cities = parseInputTsp("tsp3.txt");
        assertEquals(14662.0046407879, tsp.tspMinimumDistanceDP(generateDistanceMatrix(cities)));
    }

    @Test
    public void result() {
        assertEquals(26442.730308954753, 14409.202165641733 + 14662.0046407879 - 2 * 1314.2382487374398);
    }

    @Test
    public void travelingSalesmanTest3() {
        final TravelingSalesman tsp = new TravelingSalesman();
        List<City> cities = parseInputTsp("tsp2.txt");
        assertEquals(40.0, tsp.tspMinimumDistanceDP(generateDistanceMatrix(cities)));
    }

    @Test
    public void travelingSalesmanTest2() {
        final TravelingSalesman tsp = new TravelingSalesman();
        List<City> cities = parseInputTsp("tsp2.txt");
        assertEquals(40.0, tsp.tspMinimumDistanceNaive(cities, cities.get(0)));
    }

    @Test
    public void generateCombinations() {
        final TravelingSalesman travelingSalesman = new TravelingSalesman();
        final Set<Set<Integer>> result = new HashSet<>();

        travelingSalesman.generateCombinations(3, result, new ArrayList<>());

        assertEquals(4, result.size());
    }

    @Test
    public void generateCombinations2() {
        final TravelingSalesman travelingSalesman = new TravelingSalesman();
        List<Set<Integer>> result = travelingSalesman.generateCombination(3);

        assertEquals(7, result.size());
    }

    @Test
    public void generateCombinations3() {
        final TravelingSalesman travelingSalesman = new TravelingSalesman();
        final List<Set<Integer>> result = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            result.addAll(travelingSalesman.comb(i, 4));
        }

        assertEquals(7, result.size());
    }

    @Test
    public void generateCombinations4() {
        final TravelingSalesman travelingSalesman = new TravelingSalesman();
        final List<Set<Integer>> result = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            result.addAll(travelingSalesman.comb(i, 4));
        }

        assertEquals(7, result.size());
    }

    @Test
    public void calculateDistance4() {
        final City city1 = new City(23883.3333, 14533.3333);
        final City city2 = new City(24166.6667, 13250.0000);
        assertEquals(1314.2382487374398, calculateDistance(city1, city2));
    }

    @Test
    public void calculateTspApp() {
        List<City> cities = parseInputTspApp("tsp_app5.txt");
        TravelingSalesmanApproximation approximation = new TravelingSalesmanApproximation();

        assertEquals(188129.0, approximation.tspMinimumDistanceApproximation(cities));
    }

    @Test
    public void calculateTspApp5() {
        List<City> cities = parseInputTspApp("tsp_app.txt");
        TravelingSalesmanApproximation approximation = new TravelingSalesmanApproximation();

        assertEquals(1203406.0, approximation.tspMinimumDistanceApproximation(cities));
    }

    @Test
    public void calculateTspApp4() {
        List<City> cities = parseInputTspApp("tsp_app4.txt");
        TravelingSalesmanApproximation approximation = new TravelingSalesmanApproximation();

        assertEquals(2470.0, approximation.tspMinimumDistanceApproximation(cities));
    }

    @Test
    public void calculateTspApp2() {
        List<City> cities = parseInputTspApp("tsp_app2.txt");
        TravelingSalesmanApproximation approximation = new TravelingSalesmanApproximation();

        assertEquals(48581.0, approximation.tspMinimumDistanceApproximation(cities));
    }

    @Test
    public void calculateTspApp3() {
        List<City> cities = parseInputTspApp("tsp_app3.txt");
        TravelingSalesmanApproximation approximation = new TravelingSalesmanApproximation();

        assertEquals(15.0, approximation.tspMinimumDistanceApproximation(cities));
    }

    private double[][] generateDistanceMatrix(final List<City> cities) {

        double[][] result = new double[cities.size()][cities.size()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (i != j)
                    result[i][j] = calculateDistance(cities.get(i), cities.get(j));
            }
        }

        return result;
    }

    private double calculateDistance(City city1, City city2) {
        return Math.sqrt(Math.pow(city1.getX() - city2.getX(), 2) + Math.pow(city1.getY() - city2.getY(), 2));
    }

    private List<City> parseInputTspApp(final String fileName) {
        final File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        final List<City> cities = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String[] line = scanner.nextLine().split(" ");
                final City city = new City(Double.parseDouble(line[1]), Double.parseDouble(line[2]));
                cities.add(city);
            }

        } catch (final IOException exception) {
            exception.printStackTrace();
        }

        return cities;
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