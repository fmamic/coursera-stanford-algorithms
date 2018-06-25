package com.practice.fmamic.data.structure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Vertex implements Comparable<Vertex> {

    private Integer value;

    private Integer finish;

    private Integer distance = Integer.MAX_VALUE;

    private List<Vertex> adjacencyList = new ArrayList<>();

    public Vertex(final Integer value) {
        this.value = value;
    }

    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(final Integer finish) {
        this.finish = finish;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Vertex vertex = (Vertex) o;

        return new EqualsBuilder()
                .append(value, vertex.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }

    public int compareTo(final Vertex vertex) {
        return this.getValue().compareTo(vertex.getValue());
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(final Integer distance) {
        this.distance = distance;
    }
}
