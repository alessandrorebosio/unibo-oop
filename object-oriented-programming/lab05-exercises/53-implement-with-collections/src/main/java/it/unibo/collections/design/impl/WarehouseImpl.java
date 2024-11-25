package it.unibo.collections.design.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.collections.design.api.Product;
import it.unibo.collections.design.api.Warehouse;

public class WarehouseImpl implements Warehouse {

    private final Set<Product> set = new LinkedHashSet<>();

    @Override
    public void addProduct(Product p) {
        this.set.add(p);
    }

    @Override
    public Set<String> allNames() {
        final Set<String> s = new LinkedHashSet<>();
        for (final Product p : this.set) {
            s.add(p.getName());
        }
        return s;
    }

    @Override
    public Set<Product> allProducts() {
        return new LinkedHashSet<>(this.set);
    }

    @Override
    public boolean containsProduct(Product p) {
        return this.set.contains(p);
    }

    @Override
    public final double getQuantity(final String name) {
        for (final Product p : this.set) {
            if (p.getName() == name) {
                return p.getQuantity();
            }
        }
        return 0;
    }

}
