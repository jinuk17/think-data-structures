package io.jayden.implementation_patterns.chapter6_state;

public class Bar {

    private String feild1;
    private String feild2;
    public Foo foo;

    public void setFoo(final Foo foo) { }


    public static void main(String[] args) {
        Bar bar = new Bar();
        Foo foo = new Foo();

        foo.status = 1;
        foo.start();

        bar.foo = foo;
        bar.setFoo(foo);
    }
}



