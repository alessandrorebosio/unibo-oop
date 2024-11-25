package it.shapes.unibo.impl;

import it.shapes.unibo.api.Polygon;

public class Square implements Polygon {

    private static final int EDGES = 4;

    private final double l;

    public Square(final double l) {
        this.l = l;
    }

    @Override
    public double getArea() {
        return this.l * this.l;
    }

    @Override
    public double getPerimeter() {
        return EDGES * this.l;
    }

    @Override
    public int getEdgeCount() {
        return EDGES;
    }

}
