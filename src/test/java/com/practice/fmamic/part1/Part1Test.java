package com.practice.fmamic.part1;

import static junit.framework.TestCase.assertEquals;

import com.practice.fmamic.data.structure.Graph;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1Test {

    @Test
    public void maximumSubarrayNaiveTest() {
        MaximumSubarray subarray = new MaximumSubarray();

        int[] prices = new int[]{100, 113, 110, 85, 105, 102};

        assertEquals(20, subarray.maximumSubarrayNaive(prices));
    }

    @Test
    public void maximumSubarrayOptimizedTest() {
        MaximumSubarray subarray = new MaximumSubarray();

        int[] prices = new int[]{100, 113, 110, 85, 105, 102};

        assertEquals(20, subarray.maximumSubarrayOptimized(prices));
    }

    @Test
    public void inversionTest1() {
        InversionNumber inversionNumber = new InversionNumber();

        int[] input = getData("inversionTestFile.txt");

        assertEquals(inversionNumber.countInversionNumberNaive(input), inversionNumber.countInversionNumberOptimized(input));
    }

    @Test
    public void inversionTest2() {
        InversionNumber inversionNumber = new InversionNumber();

        int[] input = getData("inversionTestFile2.txt");

        assertEquals(inversionNumber.countInversionNumberNaive(input), inversionNumber.countInversionNumberOptimized(input));
    }

    @Test
    public void inversionTest3() {
        InversionNumber inversionNumber = new InversionNumber();
        assertEquals(inversionNumber.countInversionNumberNaive(new int[]{1, 20, 6, 4, 5}), inversionNumber.countInversionNumberOptimized(new int[]{1, 20, 6, 4, 5}));
    }

    @Test
    public void quickSort2() {
        QuickSort quickSort = new QuickSort();
        int[] input = new int[] {5,4,10,11,23,4,66,89,22};
        quickSort.sortFirstElementPivot(input, 0, input.length-1);
        input = new int[] {5,4,10,11,23,4,66,89,22};
        quickSort.sortLastElementPivot(input, 0, input.length-1);
        input = new int[] {5,4,10,11,23,4,66,89,22};
        quickSort.sortMedianPivot(input, 0, input.length-1);
    }

    @Test
    public void quickSort10() {
        QuickSort quickSort = new QuickSort();
        int[] input = getData("quicksort.txt");
        assertEquals(162085, quickSort.sortFirstElementPivot(input, 0, input.length-1));
    }


    @Test
    public void quickSort11() {
        QuickSort quickSort = new QuickSort();
        int[] input = getData("quicksort.txt");
        assertEquals(164123, quickSort.sortLastElementPivot(input, 0, input.length-1));
    }

    @Test
    public void quickSort12() {
        QuickSort quickSort = new QuickSort();
        int[] input = getData("quicksort.txt");
        assertEquals(138382, quickSort.sortMedianPivot(input, 0, input.length-1));
    }


    @Test
    public void minCutUnionFindTest1() {
        final KargerMinCut kargerMinCut = new KargerMinCut();

        int result = Integer.MAX_VALUE;
        int i = 500;
        final Graph graphData = getGraphData("kargerMinCut2");

        while (i != 0) {
            int min = kargerMinCut.minCutNumberUnionFind(graphData);
            if (min < result) {
                result = min;
            }
            i--;
            System.out.println(i + " , " + result);
        }
        assertEquals(3, result);
    }

    @Test
    public void minCutNaiveTest1() {
        final KargerMinCut kargerMinCut = new KargerMinCut();

        int result = Integer.MAX_VALUE;
        int i = 500;
        while (i != 0) {
            final Graph graphData = getGraphData("kargerMinCut2");
            int min = kargerMinCut.minCutNumber(graphData);
            if (min < result) {
                result = min;
            }
            i--;
            System.out.println(i + " , " + result);
        }
        assertEquals(3, result);
    }

    @Test
    public void minCutNaiveTest2() {
        final KargerMinCut kargerMinCut = new KargerMinCut();

        int result = Integer.MAX_VALUE;
        int i = 200;
        while (i != 0) {
            final Graph graphData = getGraphData("kargerMinCut.txt");
            int min = kargerMinCut.minCutNumber(graphData);
            if (min < result) {
                result = min;
            }
            i--;
            System.out.println(i + " , " + result);
        }
        assertEquals(17, result);
    }

    @Test
    public void minCutUnionFindTest2() {
        final KargerMinCut kargerMinCut = new KargerMinCut();

        int result = Integer.MAX_VALUE;
        int i = 200;
        final Graph graphData = getGraphData("kargerMinCut.txt");

        while (i != 0) {
            int min = kargerMinCut.minCutNumberUnionFind(graphData);
            if (min < result) {
                result = min;
            }
            i--;
            System.out.println(i + " , " + result);
        }
        assertEquals(17, result);
    }

    private Graph getGraphData(final String s) {
        File file = new File(getClass().getClassLoader().getResource(s).getFile());

        final Graph graph = new Graph();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                String[] vertices = line.split("\t");

                if (vertices.length == 1)
                    vertices = line.split(" ");

                final Graph.Vertex vertex;
                final Integer mainValue = Integer.valueOf(vertices[0]);

                if (!graph.contains(mainValue)) {
                    vertex = new Graph.Vertex(mainValue);
                    graph.addVertex(vertex);
                } else {
                    vertex = graph.getVertex(mainValue);
                }

                for (int i = 1; i < vertices.length; i++) {
                    final Integer value = Integer.valueOf(vertices[i]);
                    if (graph.contains(value)) {

                        if (!graph.containsEdge(vertex, graph.getVertex(value)))
                            graph.addEdge(vertex, graph.getVertex(value));

                    } else {
                        final Graph.Vertex newVertex = new Graph.Vertex(value);
                        graph.addVertex(newVertex);

                        if (!graph.containsEdge(vertex, newVertex))
                          graph.addEdge(vertex, newVertex);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private int[] getData(final String s) {
        File file = new File(getClass().getClassLoader().getResource(s).getFile());

        List<Integer> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(Integer.parseInt(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] input = new int[list.size()];

        int number = 0;
        for (Integer i : list) {
            input[number++] = i;
        }
        return input;
    }
}
