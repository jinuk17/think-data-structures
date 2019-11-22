package io.jayden.modern_java_in_action.chap09.chain_of_responsibility;

public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
