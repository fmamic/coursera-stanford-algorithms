package com.practice.fmamic.data.structure;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Edge {

    private Vertex source;

    private Vertex destination;

    private Integer weight = 1;

    public Edge(final Vertex source, final Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Edge(final Vertex source, final Vertex destination, final Integer weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(final Vertex source) {
        this.source = source;
    }

    public void setDestination(final Vertex destination) {
        this.destination = destination;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Edge edge = (Edge) o;

        return new EqualsBuilder()
                .append(source, edge.source)
                .append(destination, edge.destination)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(source)
                .append(destination)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("source", source)
                .append("destination", destination)
                .append("weight", weight)
                .toString();
    }
}
