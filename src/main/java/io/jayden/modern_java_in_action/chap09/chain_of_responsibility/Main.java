package io.jayden.modern_java_in_action.chap09.chain_of_responsibility;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;


public class Main {

    public static void main(String[] args) {
        final ProcessingObject<String> p1 = new HeaderTextProcessing();
        final ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?@@");
        System.out.println(result);

        /*
        *  Chain of Responsibility 의무 체인 패턴은 함수 체인 으로 표현할 수 있다.
        * */

        UnaryOperator<String> headerTextHandler = t -> t + "1. From Raoul, Mario and Alan:  ";
        UnaryOperator<String> spellCheckerProcessing = t -> t.replaceAll("labda", "lambda") + "2. " ;

        final String r = headerTextHandler.andThen(spellCheckerProcessing)
                .apply("Aren't labdas really sexy?@@");

        System.out.println(r);

        BiFunction<Long, Integer, String> s1 = (l, i) -> "aaa";
        Function<String, String> s2 = (s) -> "bbb";

        final String apply = s1.andThen(s2).apply(1L, 2);
    }
}
