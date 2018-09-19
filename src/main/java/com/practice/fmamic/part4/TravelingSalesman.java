package com.practice.fmamic.part4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.practice.fmamic.data.structure.City;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class TravelingSalesman {

    class Index {
        int startVertex;
        Set<Integer> set;

        Index(final int startVertex, final Set<Integer> set) {
            this.startVertex = startVertex;
            this.set = set;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            final Index index = (Index) o;

            return new EqualsBuilder()
                    .append(startVertex, index.startVertex)
                    .append(set, index.set)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(startVertex)
                    .append(set)
                    .toHashCode();
        }
    }

    Double tspMinimumDistanceDP(final double[][] distance) {

        final Set<Set<Integer>> combinations = new HashSet<>();
        generateCombinations(distance.length, combinations, new ArrayList<>());

        final Map<Index, Double> vertexSetDistance = new HashMap<>();
        final Map<Integer, List<Index>> indexMap = new HashMap<>();

        Set<Integer> finalCombination = null;

        for (int i = 1; i < distance.length; i++) {
            for (Set<Integer> combination : combinations) {

                if (combination.size() == distance.length - 1)
                    finalCombination = combination;

                if (combination.contains(i))
                    continue;

                final Index index = new Index(i, new HashSet<>(combination));
                vertexSetDistance.put(index, Double.MAX_VALUE);
                if (indexMap.get(combination.size()) != null) {
                    indexMap.get(combination.size()).add(index);
                } else {
                    final List<Index> indexList = new ArrayList<>();
                    indexList.add(index);
                    indexMap.put(combination.size(), indexList);
                }
            }
        }

        final Set<Integer> tempSet = new HashSet<>();
        for (int i = 0; i < distance.length - 1; i++) {
            for (final Index index : indexMap.get(i)) {
                if (index.set.isEmpty()) {
                    vertexSetDistance.put(index, distance[0][index.startVertex]);
                } else {
                    Double minimum = Double.MAX_VALUE;
                    for (final Integer key : index.set) {
                        tempSet.addAll(index.set);
                        tempSet.remove(key);
                        final Double value = vertexSetDistance.get(new Index(key, tempSet)) + distance[key][index.startVertex];

                        if (value < minimum)
                            minimum = value;

                        tempSet.clear();
                    }
                    vertexSetDistance.put(index, minimum);
                }
            }
        }

        double result = Double.MAX_VALUE;

        if (finalCombination == null)
            finalCombination = new HashSet<>();

        for (final Integer key : finalCombination) {
            tempSet.addAll(finalCombination);
            tempSet.remove(key);
            Double value = vertexSetDistance.get(new Index(key, tempSet));

            if (value + distance[key][0] < result)
                result = value + distance[key][0];
            tempSet.clear();
        }

        return result;
    }

    List<Set<Integer>> generateCombination(int n) {
        int input[] = new int[n];
        for (int i = 0; i < input.length; i++) {
            input[i] = i + 1;
        }
        List<Set<Integer>> allSets = new ArrayList<>();
        int result[] = new int[input.length];
        generateCombination(input, 0, 0, allSets, result);
        return allSets;
    }

    public static Set<Integer> bitprint(int u) {
        Set<Integer> set = new HashSet<>();
        for (int n = 0; u > 0; ++n, u >>= 1)
            if ((u & 1) > 0) set.add(n);
        return set;
    }

    public static int bitcount(int u) {
        int n;
        for (n = 0; u > 0; ++n, u &= (u - 1)) ;//Turn the last set bit to a 0
        return n;
    }

    public List<Set<Integer>> comb(int c, int n) {
        final LinkedList<Set<Integer>> s = new LinkedList<>();

        for (int u = 1; u < 1 << n; u++) {
            if (bitcount(u) == c) {
                Set<Integer> result = bitprint(u);
                if (!result.contains(0))
                    s.push(result);
            }
        }

        return s;
    }

    void generateCombination(int input[], int start, int pos, List<Set<Integer>> allSets, int result[]) {
        if (pos == input.length) {
            return;
        }
        Set<Integer> set = createSet(result, pos);
        allSets.add(set);
        for (int i = start; i < input.length; i++) {
            result[pos] = input[i];
            generateCombination(input, i + 1, pos + 1, allSets, result);
        }
    }

    private static Set<Integer> createSet(int input[], int pos) {
        if (pos == 0) {
            return new HashSet<>();
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pos; i++) {
            set.add(input[i]);
        }
        return set;
    }

    void generateCombinations(int n, Set<Set<Integer>> result, List<Integer> temp) {

        result.add(new HashSet<>(temp));

        if (n == 0)
            return;

        for (int i = n - 1; i > 0; i--) {
            if (!temp.contains(i)) {
                temp.add(i);
                generateCombinations(n - 1, result, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    Double tspMinimumDistanceNaive(final List<City> cities, final City root) {
        Set<City> visited = new HashSet<>();
        visited.add(root);
        return tspMinimumDistanceNaiveRec(cities, visited, root, root, 0.0);
    }

    Double tspMinimumDistanceNaiveRec(final List<City> cities, final Set<City> visited, final City last, final City origin, final Double current) {

        if (visited.size() == cities.size())
            return current + calculateDistance(last, origin);

        double minimum = Double.MAX_VALUE;
        for (final City city : cities) {
            if (!visited.contains(city)) {
                visited.add(city);
                minimum = Math.min(minimum, tspMinimumDistanceNaiveRec(cities, visited, city, origin, calculateDistance(city, last) + current));
                visited.remove(city);
            }
        }

        return minimum;
    }

    private double calculateDistance(City city1, City city2) {
        return Math.sqrt(Math.pow(city1.getX() - city2.getX(), 2) + Math.pow(city1.getY() - city2.getY(), 2));
    }

}