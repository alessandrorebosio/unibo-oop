package it.unibo.collections.design.impl;

import java.util.Set;
import java.util.TreeSet;

import it.unibo.collections.design.api.Product;

public class OrderedWarehouse extends WarehouseImpl {

    @Override
    public Set<Product> allProducts() {
        return new TreeSet<>(super.allProducts());
    }

}
