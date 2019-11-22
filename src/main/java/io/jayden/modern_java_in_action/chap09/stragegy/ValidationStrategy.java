package io.jayden.modern_java_in_action.chap09.stragegy;

/**
 * The interface Validation strategy.
 * 알고리즘을 나타내는 Strategy Interface
 */
public interface ValidationStrategy {
    /**
     * Execute boolean.
     *
     * @param s the s
     * @return the boolean
     */
    boolean execute(String s);
}
