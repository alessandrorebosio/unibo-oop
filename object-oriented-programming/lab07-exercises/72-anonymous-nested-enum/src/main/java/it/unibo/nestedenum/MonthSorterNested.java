package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final int DAYS_31 = 31;
    private static final int DAYS_30 = 30;
    private static final int DAYS_28 = 28;

    private enum Month {
        JANUARY(DAYS_31),
        FEBRUARY(DAYS_28),
        MARCH(DAYS_31),
        APRIL(DAYS_30),
        MAY(DAYS_31),
        JUNE(DAYS_30),
        JULY(DAYS_31),
        AUGUST(DAYS_31),
        SEPTEMBER(DAYS_30),
        OCTOBER(DAYS_31),
        NOVEMBER(DAYS_30),
        DECEMBER(DAYS_31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        public static Month fromString(final String name) {
            Objects.requireNonNull(name);
            try {
                return Month.valueOf(name);
            } catch (IllegalArgumentException e) {
                Month match = null;
                for (final Month month : Month.values()) {
                    if (month.toString().toLowerCase().startsWith(name.toLowerCase())) {
                        if (match != null) {
                            throw new IllegalArgumentException(name + " is ambiguous: ", e);
                        }
                        match = month;
                    }
                }
                if (match == null) {
                    throw new IllegalArgumentException("No match for " + name, e);
                }
                return match;
            }
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByOrder();
    }

    private class SortByDays implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            return Integer.compare(Month.fromString(o1).days, Month.fromString(o2).days);
        }

    }

    private class SortByOrder implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }

    }
}
