package org.thedoug.farkle.model;

import java.util.Iterator;

public class FaceCounter {
    private final int minValue;
    private final int maxValue;

    public FaceCounter(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public FaceCount count(Iterable<Integer> rolls) {
        return new FaceCount(rolls);
    }

    public class FaceCount implements Iterable<Integer> {
        // TODO create a generalized BoundedArrayMap for this
        private int[] faceCounts;

        public FaceCount(Iterable<Integer> roll) {
            faceCounts = countDice(roll);
        }

        private int[] countDice(Iterable<Integer> roll) {
            int[] valueCounts = new int[faceValueToIndex(maxValue) + 1];

            for (Integer face : roll) {
                valueCounts[faceValueToIndex(face)]++;
            }

            return valueCounts;
        }

        public int get(int face) {
            return faceCounts[faceValueToIndex(face)];
        }

        private int faceValueToIndex(int face) {
            return face - minValue;
        }

        public int totalCounts() {
            int total = 0;
            for (int count : faceCounts) {
                total += count;
            }
            return total;
        }

        public void put(int face, int count) {
            faceCounts[faceValueToIndex(face)] = count;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int face = minValue;

                @Override
                public boolean hasNext() {
                    return face < maxValue;
                }

                @Override
                public Integer next() {
                    return face++;
                }

                @Override
                public void remove() {
                    put(face, 0);
                }
            };
        }
    }
}
