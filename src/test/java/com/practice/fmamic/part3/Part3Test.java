package com.practice.fmamic.part3;

import static junit.framework.TestCase.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.practice.fmamic.data.structure.Job;
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
