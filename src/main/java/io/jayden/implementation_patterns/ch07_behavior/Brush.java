package io.jayden.implementation_patterns.ch07_behavior;

public interface Brush {
    void display(Shape shape);

    void displayOval(Oval oval);
    void displayRectangle(Rectangle rectangle);
}
