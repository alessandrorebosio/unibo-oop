package it.unibo.collections.comparators;

import java.util.Comparator;

public class CompareStringAsDouble implements Comparator<String> {

    @Override
    public int compare(final String o1, final String o2) {
        return Double.compare(Double.parseDouble(o1), Double.parseDouble(o2));
    }

}
