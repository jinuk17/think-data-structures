package io.jayden.implementation_patterns.ch07_behavior;

public class Canvas {

    public void displayShape1(final Shape subject, final Brush brush) {
        brush.display(subject);
    }

    public void displayShape2(final Shape subject, final Brush brush) {
        subject.displayWith(brush);
    }

    public static void main(String[] args) {
        final Canvas canvas = new Canvas();
        canvas.displayShape1(new Shape() {
            @Override
            public void displayWith(Brush brush) {
                brush.display(this);
            }
        }, new Brush() {
            @Override
            public void display(Shape shape) {
                if(shape instanceof Oval) {

                }else if(shape instanceof Rectangle) {

                }
            }

            @Override
            public void displayOval(Oval oval) {

            }

            @Override
            public void displayRectangle(Rectangle rectangle) {

            }
        });

        canvas.displayShape2(new Shape() {
            @Override
            public void displayWith(Brush brush) {

                brush.display(this);
            }
        }, new Brush() {
            @Override
            public void display(Shape shape) {

            }

            @Override
            public void displayOval(Oval oval) {

            }

            @Override
            public void displayRectangle(Rectangle rectangle) {

            }
        });
    }
}
