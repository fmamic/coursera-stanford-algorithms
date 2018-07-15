package com.practice.fmamic.part3;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanCode {


    int maximumLengthCode(final Map<Integer, Long> input) {

        final PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new Comparator<HuffmanNode>() {
            @Override
            public int compare(final HuffmanNode o1, final HuffmanNode o2) {
                return o1.weight.compareTo(o2.weight);
            }
        });

        for (final Map.Entry<Integer, Long> entry : input.entrySet()) {
            queue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        int result = 0;
        while (!queue.isEmpty()) {

            HuffmanNode huffman1 = queue.poll();

            if (queue.peek() == null) {
                result++;
                break;
            }

            HuffmanNode huffman2 = queue.poll();

            queue.offer(new HuffmanNode(huffman1.key, huffman1.weight + huffman2.weight));
            result++;
        }

        return result;
    }

    int minimumLengthCode(final Map<Integer, Integer> input) {

        return 0;
    }

    static class HuffmanNode {
        Integer key;
        Long weight;

        HuffmanCode left;
        HuffmanCode right;

        HuffmanNode(final Integer key, final Long weight) {
            this.key = key;
            this.weight = weight;
        }
    }

}
