package com.practice.fmamic.data.structure;

import java.util.*;

public class MapPriorityQueue {

    private final Map<String, Item> map = new HashMap<>();
    private final Queue<Item> queue = new PriorityQueue<>();

    // O(logN)
    public Item extractMin() {
        Item item = queue.poll();
        map.remove(item.getKey());
        return item;
    }

    // O(logN)
    public void add(final Item item) {
        map.put(item.getKey(), item);
        queue.offer(item);
    }

    // O(1)
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    // O(logN)
    public void decrease(String key) {
        if (map.get(key) == null)
            return;

        map.get(key).setValue(map.get(key).getValue() - 1);
    }

    public static class Item implements Comparable<Item> {

        private String key;

        private Integer value;

        public Item(final String key, final Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(key, item.key);
        }

        @Override
        public int hashCode() {

            return Objects.hash(key);
        }

        public void setValue(Integer value) {
            this.value = value;

        }

        @Override
        public int compareTo(Item o) {
            return this.getValue().compareTo(o.getValue());
        }
    }
}
