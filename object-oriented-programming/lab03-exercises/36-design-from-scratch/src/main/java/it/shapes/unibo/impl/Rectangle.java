package it.shapes.unibo.impl;

import it.shapes.unibo.api.Polygon;

public class Rectangle implements Polygon {

    private static final int EDGES = 4;

    private final double b;
    private final double h;

    public Rectangle(final double b, final double h) {
        this.b = b;
        this.h = h;
    }

    @Override
    public double getArea() {
        return this.b * this.h;
    }

    @Override
    public double getPerimeter() {
        return (this.b + this.h) * 2;
    }

    @Override
    public int getEdgeCount() {
        return EDGES;
    }

}
