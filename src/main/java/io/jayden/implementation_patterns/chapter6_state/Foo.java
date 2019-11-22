package io.jayden.implementation_patterns.chapter6_state;

public class Foo {

    private int width;
    private int height;
    private int area;


    public int status;

    public void setWidth(final int width) {
        this.width = width;
        area = this.height * width;
    }

    public void optionParameter(String a1){}
    public void optionParameter(String a1, String a2){}
    public void optionParameter(String a1, String a2, String a3){}

    /**
    * case class foo(a1: String, a2: String = 10)
    * foo("aaa")
    * foo(a1 = "aaa")
    * */

    void variableParameter(Bar... bars) { }

    public void start() {
        status = 1;
    }

}