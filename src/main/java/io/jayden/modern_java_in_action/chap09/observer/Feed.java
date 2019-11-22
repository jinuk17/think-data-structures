package io.jayden.modern_java_in_action.chap09.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(observer -> observer.notify(tweet));
    }

    public static void main(String[] args) {
        final Feed feed = new Feed();
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.registerObserver(new NYTimes());

        feed.notifyObservers("The queen said her favourite book is Modern Java in Action.");

        final Feed feed2 = new Feed();
        feed2.registerObserver(tweet -> {
            if(tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news ! " + tweet);
            }
        });
        feed2.registerObserver(tweet -> {
            if(tweet != null && tweet.contains("queen")) {
                System.out.println(("Yet more news from London ..." + tweet));
            }
        });
        feed2.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
    }
}
