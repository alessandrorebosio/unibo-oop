package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * Terminal {@link Controller} implementation.
 */
public final class SimpleController implements Controller {

    private final List<String> history = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextString(final String nextString) {
        this.nextString = Objects.requireNonNull(nextString, "nextString must not be null");
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> history() {
        return List.copyOf(history);
    }

    @Override
    public void printCurretString() {
        if (this.nextString == null) {
            throw new IllegalStateException("String not set");
        }
        this.history.add(nextString);
        System.out.println(this.nextString); // NOPMD: just an exercises.
    }

}
