package com.practice.fmamic.data.structure;

public class Job {

    private Integer weight;

    private Integer length;

    public Job(final Integer weight, final Integer length) {
        this.weight = weight;
        this.length = length;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getScoreNonOptimal() {
        return this.weight - this.length;
    }

    public Double getScoreOptimal() {
        return this.weight / (double) this.length;
    }
}
