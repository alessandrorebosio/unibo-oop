package it.unibo.collections.design.impl;

import it.unibo.collections.design.api.Product;

public class ComparableProduct extends ProductImpl implements Comparable<Product> {

    public ComparableProduct(final String name, final double quantity) {
        super(name, quantity);
    }

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }

}
