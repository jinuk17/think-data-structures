package io.jayden.implementation_patterns.ch09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections09 {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Collections{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList();
        final Collections09 b = new Collections09();
        b.setName("aaa");
        a.add(1);
        a.add(2);
        a.add(3);

        test(a);
        test(b);
        System.out.println(a);
        System.out.println(b);

    }

    private static void test(List<Integer> a) {

        a.add(2);
    }

    private static void test(Collections09 b) {
        b = new Collections09();
        b.setName("bbb");
    }
}
