package com.practice.fmamic.part4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TwoSat {

    static class Clause {
        int a;
        int b;
        boolean result;
    }

    boolean satisfiability(final List<Clause> clauses) {

        filterClauses(clauses);

        int n = clauses.size();
        for (int i = 0; i < Math.log(n); i++) {

            Map<Integer, Boolean> valueMap = new HashMap<>();
            initialRandomAssignment(clauses, valueMap);

            for (int k = 0; k < 2 * Math.pow(n, 2); k++) {
                Clause result = checkResult(clauses);
                if (result == null)
                    return true;

                boolean position = (int) (Math.random() * 2) != 0;

                if (position) {
                    valueMap.put(result.a < 0 ? -result.a : result.a, !valueMap.get(result.a < 0 ? -result.a : result.a));
                } else {
                    valueMap.put(result.b < 0 ? -result.b : result.b, !valueMap.get(result.b < 0 ? -result.b : result.b));
                }

                for (final Clause clause : clauses) {
                    boolean val1 = valueMap.get(clause.a < 0 ? -clause.a : clause.a);
                    boolean val2 = valueMap.get(clause.b < 0 ? -clause.b : clause.b);
                    clause.result = ((clause.a < 0) != val1) || ((clause.b < 0) != val2);
                }

            }
        }

        return false;
    }

    private Clause checkResult(final List<Clause> clauses) {
        Clause result = null;
        for (Clause clause : clauses) {
            if (!clause.result) {
                result = clause;
                break;
            }
        }
        return result;
    }

    private void initialRandomAssignment(final List<Clause> clauses, final Map<Integer, Boolean> valueMap) {
        for (final Clause clause : clauses) {

            boolean val1 = (int) (Math.random() * 2) != 0;
            boolean val2 = (int) (Math.random() * 2) != 0;

            if (clause.a < 0 && valueMap.get(-clause.a) == null) {
                valueMap.put(-clause.a, val1);
                val1 = !val1;
            } else if (clause.a > 0 && valueMap.get(clause.a) == null) {
                valueMap.putIfAbsent(clause.a, val1);
            } else {
                if (clause.a < 0)
                    val1 = !valueMap.get(-clause.a);
                else
                    val1 = valueMap.get(clause.a);
            }

            if (clause.b < 0 && valueMap.get(-clause.b) == null) {
                valueMap.put(-clause.b, val2);
                val2 = !val2;
            } else if (clause.b > 0 && valueMap.get(clause.b) == null) {
                valueMap.putIfAbsent(clause.b, val2);
            } else {
                if (clause.b < 0)
                    val2 = !valueMap.get(-clause.b);
                else
                    val2 = valueMap.get(clause.b);
            }

            clause.result = val1 || val2;
        }
    }

    int filterClauses(final List<Clause> clauses) {
        int clausesSize = clauses.size() + 1;
        int[] temp = new int[clausesSize];

        final Set<Integer> remove = new HashSet<>();
        remove.add(11);

        while (!remove.isEmpty()) {

            remove.clear();

            for (final Clause clause : clauses) {
                checkParam(temp, clause.a);
                checkParam(temp, clause.b);
            }

            for (int k = 0; k < temp.length; k++) {
                if (temp[k] == 1 || temp[k] == 2) {
                    remove.add(k);
                }
            }

            clauses.removeIf(clause -> remove.contains(clause.a) || remove.contains(clause.b) || remove.contains(-clause.a) || remove.contains(-clause.b));
            temp = new int[clausesSize];
        }

        return clauses.size();
    }

    private void checkParam(final int[] temp, final int clause) {
        if (clause < 0) {
            if (temp[-clause] == 1 || temp[-clause] == 3) {
                temp[-clause] = 3;
            } else {
                temp[-clause] = 2;
            }
        } else {
            if (temp[clause] == 2 || temp[clause] == 3) {
                temp[clause] = 3;
            } else {
                temp[clause] = 1;
            }
        }
    }
}