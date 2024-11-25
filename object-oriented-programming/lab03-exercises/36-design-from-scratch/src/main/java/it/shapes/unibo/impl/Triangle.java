package it.shapes.unibo.impl;

import it.shapes.unibo.api.Polygon;

public class Triangle implements Polygon {

    public static final int EDGES = 3;

    private final double l1;
    private final double l2;
    private final double l3;

    public Triangle(final double l1, final double l2, final double l3) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
    }

    @Override
    public double getArea() {
        final double s = this.getPerimeter() / 2;
        return Math.sqrt(s * (s - this.l1) * (s - this.l2) * (s - this.l3));
    }

    @Override
    public double getPerimeter() {
        return this.l1 + this.l2 + this.l3;
    }

    @Override
    public int getEdgeCount() {
        return EDGES;
    }

}
