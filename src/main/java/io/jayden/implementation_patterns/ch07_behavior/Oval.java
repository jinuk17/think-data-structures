package io.jayden.implementation_patterns.ch07_behavior;

public class Oval implements Shape {
    @Override
    public void displayWith(Brush brush) {
        int s = 0x0080;


        brush.displayOval(this);
    }
}
