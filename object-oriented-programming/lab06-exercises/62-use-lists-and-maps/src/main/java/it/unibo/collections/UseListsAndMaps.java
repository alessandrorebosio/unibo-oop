package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private final static int START = 1000;
    private final static int END = 2000;
    private final static int ELEMENTS = 100000;
    private final static int READS = 10000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *          unused
     */
    public static void main(final String... s) {
        long time;

        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> array = new ArrayList<>();
        for (int i = START; i < END; i++) {
            array.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> list = new LinkedList<>(array);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final var temp = array.getFirst();
        array.set(0, array.getLast());
        array.set(array.size() - 1, temp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final var elem : array) {
            System.out.println(elem + " ");
        }

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        time = System.currentTimeMillis();
        for (int i = 0; i < ELEMENTS; i++) {
            array.addFirst(i);
        }
        time = System.nanoTime() - time;
        System.out.println("Inserting " + ELEMENTS + " elements as first in an ArrayList took " + time
                + "ns (" + TimeUnit.NANOSECONDS.toMillis(time) + "ms)");

        time = System.currentTimeMillis();
        for (int i = 0; i < ELEMENTS; i++) {
            list.addFirst(i);
        }
        time = System.nanoTime() - time;
        System.out.println("Inserting " + ELEMENTS + " elements as first in an ArrayList took " + time
                + "ns (" + TimeUnit.NANOSECONDS.toMillis(time) + "ms)");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < READS; i++) {
            array.get(array.size() / 2);
        }
        time = System.nanoTime() - time;
        System.out.println("Reading " + READS + " elements in the middle of an ArrayList took " + time
                + "ns (" + TimeUnit.NANOSECONDS.toMillis(time) + "ms)");

        time = System.nanoTime();
        for (int i = 0; i < READS; i++) {
            list.get(list.size() / 2);
        }
        time = System.nanoTime() - time;
        System.out.println("Reading " + READS + " elements in the middle of an ArrayList took " + time
                + "ns (" + TimeUnit.NANOSECONDS.toMillis(time) + "ms)");
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> world = new HashMap<>();
        world.put("Africa", 1_110_635_000L);
        world.put("Americas", 972_005_000L);
        world.put("Antarctica", 0L);
        world.put("Asia", 4_298_723_000L);
        world.put("Europe", 742_452_000L);
        world.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        long total = 0;
        for (final long population : world.values()) {
            total += population;
        }
        System.out.println("We are ~" + total);
    }
}
