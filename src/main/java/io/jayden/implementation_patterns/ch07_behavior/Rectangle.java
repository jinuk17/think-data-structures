package io.jayden.implementation_patterns.ch07_behavior;

public class Rectangle implements Shape {
    @Override
    public void displayWith(Brush brush) {
        brush.displayRectangle(this);
    }
}
