package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.Predicate;
import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    final private T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements) {
        this(elements, new Predicate<T>() {

            @Override
            public boolean test(final T elem) {
                return true;
            }

        });
    }

    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter) {
        this.elements = Arrays.copyOf(elements, elements.length);
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorWithPolicy();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
    }

    private class IteratorWithPolicy implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            while (this.index < elements.length) {
                if (filter.test(elements[this.index])) {
                    return true;
                }
                this.index++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return elements[this.index++];
            }
            throw new NoSuchElementException();
        }
    }

}
