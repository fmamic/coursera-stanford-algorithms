package com.practice.fmamic.part4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.practice.fmamic.data.structure.City;

class TravelingSalesman {

    Double tspMinimumDistanceDP(final List<City> cities, final City root) {

        return 0d;
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
                minimum = Math.min(minimum, tspMinimumDistanceNaiveRec(cities, visited, city, origin,calculateDistance(city, last) + current));
                visited.remove(city);
            }
        }

        return minimum;
    }

    private double calculateDistance(City city1, City city2) {
        return Math.sqrt(Math.pow(city1.getX() - city2.getX(), 2) + Math.pow(city1.getY() - city2.getY(), 2));
    }

}