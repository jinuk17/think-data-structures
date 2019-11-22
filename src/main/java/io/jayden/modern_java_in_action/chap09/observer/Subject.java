package io.jayden.modern_java_in_action.chap09.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
