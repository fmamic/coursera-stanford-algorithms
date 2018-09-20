package com.practice.fmamic.part4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.practice.fmamic.data.structure.City;

class TravelingSalesmanApproximation {

    Double tspMinimumDistanceApproximation(final List<City> cities) {

        final Set<Integer> visited = new HashSet<>();

        double result = 0.0;
        int current = 0;
        int minimumIndex = 0;

        while (visited.size() < cities.size() - 1) {

            double minimum = Double.MAX_VALUE;
            minimumIndex = current;
            for (int i = 0; i < cities.size(); i++) {

                if (minimum < Math.abs(cities.get(current).getX() - cities.get(i).getX())) {
                    break;
                }

                if (i != current && !visited.contains(i)) {
                    double distance = calculateDistance(cities.get(current), cities.get(i));
                    if (minimum > Math.sqrt(distance)) {
                        minimum = Math.sqrt(distance);
                        minimumIndex = i;
                    }
                }
            }

            visited.add(current);
            current = minimumIndex;
            result += minimum;
        }

        return Math.floor(result + Math.sqrt(calculateDistance(cities.get(minimumIndex), cities.get(0))));
    }

    private double calculateDistance(City city1, City city2) {
        return Math.pow(city1.getX() - city2.getX(), 2) + Math.pow(city1.getY() - city2.getY(), 2);
    }
}
