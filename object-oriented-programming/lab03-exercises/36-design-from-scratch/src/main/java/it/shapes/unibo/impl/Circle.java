package it.shapes.unibo.impl;

import it.shapes.unibo.api.Shape;

public class Circle implements Shape {
    private final double radius;

    public Circle(final double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return (this.radius * this.radius) * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

}
