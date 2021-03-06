package com.practice.fmamic.part3;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanCode {

    HuffmanNode root;

    long maximumLengthCode(final Map<Integer, Long> input) {
        return maxDepth(buildHuffmanCodeTree(input)) - 1;
    }

    int minimumLengthCode(final Map<Integer, Long> input) {
        return minDepth(buildHuffmanCodeTree(input)) - 1;
    }

    public String decode(final String encoded) {

        char[] array = encoded.toCharArray();
        StringBuilder result = new StringBuilder();

        HuffmanNode node = this.root;

        for (final char anArray : array) {

            if (node.key1 == null) {
                result.append(node.key2);
                node = this.root;
                continue;
            }

            if (node.key2 == null) {
                result.append(node.key1);
                node = this.root;
                continue;
            }

            if ('0' == anArray) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node.key1 != null)
            result.append(node.key1);

        if (node.key2 != null)
            result.append(node.key2);

        return result.toString();
    }

    // O(n)
    private int maxDepth(final HuffmanNode node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            return lDepth > rDepth ? lDepth + 1 : rDepth + 1;
        }
    }

    private int minDepth(final HuffmanNode node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = minDepth(node.left);
            int rDepth = minDepth(node.right);

            return lDepth > rDepth ? rDepth + 1 : lDepth + 1;
        }
    }

    private HuffmanNode buildHuffmanCodeTree(final Map<Integer, Long> input) {
        // O(nLogN)
        final PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new Comparator<HuffmanNode>() {
            @Override
            public int compare(final HuffmanNode o1, final HuffmanNode o2) {
                return o1.weight.compareTo(o2.weight);
            }
        });

        for (final Map.Entry<Integer, Long> entry : input.entrySet()) {
            queue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        HuffmanNode root = null;
        while (!queue.isEmpty()) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            if (left == null || right == null) {
                root = left != null ? left : right;
                break;
            }

            HuffmanNode merge = new HuffmanNode(left.key1, right.key1, left.weight + right.weight, left, right);

            queue.add(merge);
        }

        this.root = root;
        return root;
    }

    static class HuffmanNode {

        Integer key1;
        Integer key2;

        Long weight;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(final Integer key, final Long value) {
            this.key1 = key;
            this.weight = value;
        }

        HuffmanNode(final Integer key1, final Integer key2, final long weight, final HuffmanNode left, final HuffmanNode right) {
            this.key1 = key1;
            this.key2 = key2;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }
    }
}
