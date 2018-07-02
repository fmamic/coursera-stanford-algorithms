package com.practice.fmamic.part3;

import static junit.framework.TestCase.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.practice.fmamic.data.structure.Graph;
import com.practice.fmamic.data.structure.Job;
import com.practice.fmamic.data.structure.Vertex;
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
    public void primsSpanningTreeTest() {
        final PrimsMinSpanningTree primsMinSpanningTree = new PrimsMinSpanningTree();
        final Graph graph = getGraphData("MST.txt");
        assertEquals(11, primsMinSpanningTree.calculateMSTCost(graph));
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


}
