package it.unibo.collections.sets;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Example class using {@link java.util.Set}.
 *
 */
public final class UseSet {

    private static final int ELEMS = 20;

    private UseSet() {
    }

    /**
     * @param args
     *             ignored
     */
    public static void main(final String[] args) {
        /*
         * Considering the content of "UseCollection, write a program which, in
         * order:
         *
         * 1) Builds a TreeSet containing Strings
         */
        final TreeSet<String> treeSet = new TreeSet<>();
        /*
         * 2) Populates such Collection with all the Strings representing numbers
         * ranging from "1" to
         * "20" (both included)
         */
        for (int i = 0; i < ELEMS; i++) {
            treeSet.add(Integer.toString(i));
        }
        /*
         * 3) Prints its content
         */
        System.out.println(treeSet);
        /*
         * 4) Removes all those strings whose represented number is divisible by three.
         * Note: the method removeIf(Predicate) is not allowed.
         */
        final var iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            if (Integer.parseInt(iterator.next()) % 3 == 0) {
                iterator.remove();
            }
        }
        /*
         * 5) Prints the content of the Set using a for-each construct
         */
        String output = "";
        for (final String elem : treeSet) {
            output += (elem) + " ";
        }
        System.out.println(output);
        /*
         * 6) Verifies whether all the numbers left in the set are even
         */
        System.out.println("Contains even number? " + containsEvenNumber(treeSet));
    }

    private static boolean containsEvenNumber(final Collection<String> target) {
        for (final var element : target) {
            if (Integer.parseInt(element) % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
