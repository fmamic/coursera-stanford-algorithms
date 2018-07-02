package com.practice.fmamic.part3;

import java.util.Comparator;
import java.util.List;

import com.practice.fmamic.data.structure.Job;

class SchedulingApplication {

    long scheduleNonOptimalSum(final List<Job> jobs) {

        jobs.sort(Comparator.comparing(Job::getScoreNonOptimal).reversed().thenComparing(Comparator.comparing(Job::getWeight).reversed()));

        int time = 0;
        long result = 0;

        for (final Job job : jobs) {
            time += job.getLength();
            result += job.getWeight() * time;
        }

        return result;
    }

    long scheduleOptimalSum(final List<Job> jobs) {

        jobs.sort(Comparator.comparing(Job::getScoreOptimal).reversed().thenComparing(Comparator.comparing(Job::getWeight).reversed()));

        int time = 0;
        long result = 0;

        for (final Job job : jobs) {
            time += job.getLength();
            result += job.getWeight() * time;
        }

        return result;
    }
}
