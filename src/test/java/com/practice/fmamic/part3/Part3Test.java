package com.practice.fmamic.part3;

import static junit.framework.TestCase.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.practice.fmamic.data.structure.DisjointSet;
import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.Job;
import com.practice.fmamic.data.structure.Vertex;
import org.junit.Ignore;
import org.junit.Test;

public class Part3Test {

    @Test
    public void schedulingApplicationTest1() {
        final SchedulingApplication application = new SchedulingApplication();

        final List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(3, 5));
        jobs.add(new Job(1, 2));

        assertEquals(23, application.scheduleNonOptimalSum(jobs));
    }

    @Test
    public void schedulingApplicationTest2() {
        final SchedulingApplication application = new SchedulingApplication();

        final List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(3, 5));
        jobs.add(new Job(1, 2));
        jobs.add(new Job(5, 7));

        assertEquals(89, application.scheduleNonOptimalSum(jobs));
    }

    @Test
    public void schedulingApplicationTestOptimal1() {
        final SchedulingApplication application = new SchedulingApplication();

        final List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(3, 5));
        jobs.add(new Job(1, 2));

        assertEquals(22, application.scheduleOptimalSum(jobs));
    }

    @Test
    public void schedulingApplicationTestOptimal2() {
        final SchedulingApplication application = new SchedulingApplication();

        final List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(3, 5));
        jobs.add(new Job(1, 2));
        jobs.add(new Job(5, 7));

        assertEquals(85, application.scheduleOptimalSum(jobs));
    }


    @Test
    public void schedulingApplicationTest3() {
        SchedulingApplication application = new SchedulingApplication();
        List<Job> jobs = getJobsData("jobs.txt");
        assertEquals(69119377652L, application.scheduleNonOptimalSum(jobs));
    }

    @Test
    public void schedulingApplicationTest3Optimal() {
        SchedulingApplication application = new SchedulingApplication();
        List<Job> jobs = getJobsData("jobs.txt");
        assertEquals(67311454237L, application.scheduleOptimalSum(jobs));
    }

    @Test
    public void primsSpanningTreeTest2() {
        final PrimsMinSpanningTree primsMinSpanningTree = new PrimsMinSpanningTree();

        final Graph graph = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 1);
        graph.addEdge(v2, v3, 2);
        graph.addEdge(v3, v4, 4);
        graph.addEdge(v2, v4, 3);
        graph.addEdge(v3, v5, 5);
        graph.addEdge(v5, v1, 6);

        assertEquals(11, primsMinSpanningTree.calculateMSTCost(graph));
    }

    @Test
    public void primsSpanningTreeTest1() {
        final PrimsMinSpanningTree primsMinSpanningTree = new PrimsMinSpanningTree();
        final Graph graph = getGraphData("MST.txt");
        assertEquals(-3612829, primsMinSpanningTree.calculateMSTCost(graph));
    }

    @Test
    public void kruskalSpanningTreeTest1() {
        final KruskalMinSpanningTree kruskalMinSpanningTree = new KruskalMinSpanningTree();
        final Graph graph = getGraphData("MST.txt");
        assertEquals(-3612829, kruskalMinSpanningTree.calculateMSTNaive(graph));
    }

    @Test
    public void clusteringTest() {
        final Clustering clustering = new Clustering();
        final Graph graph = getGraphData("clustering1.txt");
        assertEquals(106, clustering.maxClusterDistance(graph, 4));
    }

    @Ignore
    @Test
    public void clusteringTest2() {
        final Clustering clustering = new Clustering();
        final Map<Integer, Vertex> input = getClusterData("clustering2.txt");
        assertEquals(6118, clustering.hammingClusterDistance(input));
    }

    @Test
    public void clusteringTest4() {
        final Clustering clustering = new Clustering();
        final Map<Integer, Vertex> input = getClusterData("clustering4.txt");
        assertEquals(6, clustering.hammingClusterDistance(input));
    }

    @Test
    public void clusteringTest3() {
        final Clustering clustering = new Clustering();
        final Graph graph = getGraphData("clustering3.txt");
        assertEquals(90, clustering.maxClusterDistance(graph, 4));
    }

    @Test
    public void flipBit() {
        final Clustering clustering = new Clustering();
        assertEquals(12, clustering.flipBit(8, 2));
    }

    @Test
    public void hammingDistanceTest() {
        final Clustering clustering = new Clustering();
        assertEquals(1, clustering.distance(9, 11));
    }

    @Test
    public void disjointSetTest() {
        final DisjointSet disjointSet = new DisjointSet(8);
        disjointSet.union(1,5);
        disjointSet.union(1,6);

        assertEquals(1, disjointSet.find(5));
        assertEquals(1, disjointSet.find(6));

        assertEquals(6, disjointSet.getSize());

        disjointSet.union(2,3);
        disjointSet.union(3,4);

        assertEquals(2, disjointSet.find(3));
        assertEquals(2, disjointSet.find(4));

        disjointSet.union(1,2);

        assertEquals(1, disjointSet.find(2));
        assertEquals(1, disjointSet.find(5));
        assertEquals(1, disjointSet.find(6));
        assertEquals(1, disjointSet.find(3));

        assertEquals(7, disjointSet.find(7));

        disjointSet.union(1, 7);

        assertEquals(1, disjointSet.find(7));

        assertEquals(2,disjointSet.getSize());

        disjointSet.union(1, 7);

        assertEquals(2,disjointSet.getSize());
    }

    @Test
    public void huffmanCodeTest1() {
        HuffmanCode huffmanCode = new HuffmanCode();
        assertEquals(19, huffmanCode.maximumLengthCode(getHuffmanCodeData("huffman.txt")));
        assertEquals(9, huffmanCode.minimumLengthCode(getHuffmanCodeData("huffman.txt")));
    }

    @Test
    public void huffmanCodeTest2() {
        HuffmanCode huffmanCode = new HuffmanCode();
        assertEquals(5, huffmanCode.maximumLengthCode(getHuffmanCodeData("huffman2.txt")));
        assertEquals(2, huffmanCode.minimumLengthCode(getHuffmanCodeData("huffman2.txt")));
    }

    @Test
    public void huffmanCodeTest() {
        HuffmanCode huffmanCode = new HuffmanCode();
        assertEquals(2, huffmanCode.minimumLengthCode(getHuffmanCodeData("huffman2.txt")));

        assertEquals("5", huffmanCode.decode("00"));
    }

    @Test
    public void mwisTest1() {
        MaximumWIS maximumWIS = new MaximumWIS();
        assertEquals(2616, maximumWIS.calculateMaximumWISValue(getMaximumWISData("mwis1.txt")));
        assertEquals(2616, maximumWIS.calculateMaximumWISNaive(getMaximumWISData("mwis1.txt")));
        assertEquals("0101010101 ", maximumWIS.calculateMaximumWISSolution(getMaximumWISData("mwis1.txt")));
    }

    @Test
    public void mwisTest2() {
        MaximumWIS maximumWIS = new MaximumWIS();
        assertEquals(2533, maximumWIS.calculateMaximumWISValue(getMaximumWISData("mwis2.txt")));
        assertEquals("1010010010", maximumWIS.calculateMaximumWISSolution(getMaximumWISData("mwis2.txt")));
    }

    @Test
    public void mwisTest3() {
        MaximumWIS maximumWIS = new MaximumWIS();
        assertEquals(2955353732L, maximumWIS.calculateMaximumWISValue(getMaximumWISData("mwis.txt")));
        assertEquals("1010100101001010010101010101001010010101010101010101010101010101010010010010101010101001010101010101001001010101001010010101010100101001001010101010101010101001010101010100101010101010101001001010101010101010101001010100101001010101010101010010101010010101010010101010101010101010101010101001010101010101010101010101010010101010101010101010101010101010101010101010101010101010101010101010101010101010010101010100101010010100101010101010101010101010101001010101010101010101010101001010100101010101010100101001010101001010101010101010010010101010100101010101010100100100101010101010100101010101010101010101010101001010101010101001010101001010101010101001010101010101010010101001001001010101010100101010010101010100101010101010010101001010101010101001010100101001001001010101010101001001010101001010101010100100100101010010101010101001010010101010010010101010101010010101001010010101010101010101010101010010101010101010101010010010100101010101010101010101010101010101010010101010101010101010101010100101", maximumWIS.calculateMaximumWISSolution(getMaximumWISData("mwis.txt")));
    }

    @Test
    public void knapsackTest1() {
        Knapsack knapsack = new Knapsack();
        assertEquals(14, knapsack.maximumKnapsackValueNaive(new int[] {4, 2, 8, 10}, new int[] {4, 2, 5, 2}, 6));
    }

    @Test
    public void knapsackTest2() {
        Knapsack knapsack = new Knapsack();
        KnapsackData knapsackData = getKnapsackData("knapsack2");
        assertEquals(147, knapsack.maximumKnapsackValueNaive(knapsackData.values, knapsackData.weights, knapsackData.total));
    }

    @Test
    public void knapsackTest3() {
        Knapsack knapsack = new Knapsack();
        KnapsackData knapsackData = getKnapsackData("knapsack2");
        assertEquals(147, knapsack.maximumKnapsackValueMemo(knapsackData.values, knapsackData.weights, knapsackData.total));
    }

    @Test
    public void knapsackTest4() {
        Knapsack knapsack = new Knapsack();
        KnapsackData knapsackData = getKnapsackData("knapsack1.txt");
        assertEquals(2493893, knapsack.maximumKnapsackValueDp(knapsackData.values, knapsackData.weights, knapsackData.total));
    }

    @Test
    public void knapsackTest5() {
        Knapsack knapsack = new Knapsack();
        KnapsackData knapsackData = getKnapsackData("knapsack2");
        assertEquals(147, knapsack.maximumKnapsackValueDp(knapsackData.values, knapsackData.weights, knapsackData.total));
    }

    @Test
    public void knapsackTest6() {
        Knapsack knapsack = new Knapsack();
        KnapsackData knapsackData = getKnapsackData("knapsack_big.txt");
        assertEquals(4243395, knapsack.maximumKnapsackValueDp(knapsackData.values, knapsackData.weights, knapsackData.total));
    }

    @Test
    public void knapsackTest8() {
        Knapsack knapsack = new Knapsack();
        assertEquals("41", knapsack.maximumKnapsackSolutionDp(new int[] {4, 2, 8, 10}, new int[] {4, 2, 5, 2}, 6));
    }

    @Test
    public void sequenceAlignmentTest() {
        SequenceAlignment sequenceAlignment = new SequenceAlignment();
        assertEquals(6, sequenceAlignment.calculateNaiveValue("GCATGCU", "GATTACA"));
        assertEquals(6, sequenceAlignment.calculateOptimalValue("GCATGCU", "GATTACA"));
        assertEquals("U-CG-T-ACG#-AC-ATTA-G", sequenceAlignment.calculateOptimalSolution("GCATGCU", "GATTACA"));
    }

    private KnapsackData getKnapsackData(final String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        KnapsackData knapsackData = null;

        try (Scanner scanner = new Scanner(file)) {
            String length = scanner.nextLine();
            knapsackData = new KnapsackData(Integer.parseInt(length.split(" ")[1]), Integer.parseInt(length.split(" ")[1]), Integer.parseInt(length.split(" ")[0]));

            int index = 0;
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                knapsackData.values[index] = Integer.parseInt(line.split(" ")[0]);
                knapsackData.weights[index] = Integer.parseInt(line.split(" ")[1]);
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return knapsackData;
    }

    private long[] getMaximumWISData(String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());

        long[] result = null;

        try (Scanner scanner = new Scanner(file)) {

            String length = scanner.nextLine();
            result = new long[Integer.parseInt(length)];

            int index = 0;
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                result[index++] = Long.parseLong(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Map<Integer, Long> getHuffmanCodeData(String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        final Map<Integer, Long> map = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            int key = 0;
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                map.put(key++, Long.parseLong(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    private Map<Integer, Vertex> getClusterData(final String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        final Map<Integer, Vertex> map = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            int key = 0;
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine().replaceAll(" ", "");
                final int value = Integer.parseInt(line, 2);

                if (!map.containsKey(value)) {
                    final Vertex vertex = new Vertex(key++);
                    map.put(value, vertex);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    private Graph getGraphData(final String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        final Graph graph = new Graph();

        try (Scanner scanner = new Scanner(file)) {

            scanner.nextLine();

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();

                String[] split1 = line.split(" ");

                final Vertex source;

                if (!graph.contains(Integer.valueOf(split1[0]))) {
                    source = new Vertex(Integer.valueOf(split1[0]));
                } else {
                    source = graph.getVertex(Integer.valueOf(split1[0]));
                }

                graph.addVertex(source);


                final Vertex destination;

                if (!graph.contains(Integer.valueOf(split1[1]))) {
                    destination = new Vertex(Integer.valueOf(split1[1]));
                } else {
                    destination = graph.getVertex(Integer.valueOf(split1[1]));
                }

                graph.addVertex(destination);
                graph.addEdge(source, destination, Integer.valueOf(split1[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }


    private List<Job> getJobsData(final String s) {
        final File file = new File(getClass().getClassLoader().getResource(s).getFile());
        final List<Job> jobs = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                String[] parts = line.split(" ");
                jobs.add(new Job(Integer.valueOf(parts[0]), Integer.valueOf(parts[1])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jobs;
    }


    private class KnapsackData {

        int[] weights;
        int[] values;
        int total;

        KnapsackData(final int weights, final int values, final int total) {
            this.weights = new int[weights];
            this.values = new int[values];
            this.total = total;
        }

        public int[] getWeights() {
            return weights;
        }

        public void setValues(final int[] values) {
            this.values = values;
        }

    }
}
