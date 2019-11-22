package io.jayden.modern_java_in_action.chap09.observer;

public class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news ! " + tweet);
        }
    }
}
