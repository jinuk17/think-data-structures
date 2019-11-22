package io.jayden.thinkdast;

import io.jayden.thinkdast.profiler.Profiler;
import io.jayden.thinkdast.profiler.Profiler.Timeable;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProfileArrayListAdd {

    public static void main(String[] args) {

        profileArrayListAddEnd();
        profileArrayListAddBeginning();
        profileLinkedListAddEnd();
        profileLinkedListAddBeginning();
    }

    private static void profileArrayListAddEnd() {
        Timeable timeable = new Timeable() {

            List<String> list;
            @Override
            public void setup(int n) {
                list = new ArrayList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0; i<n; i++) {
                    list.add("a string");
                }
            }
        };

        profiler("ArrayList add end", timeable);

    }

    private static void profileArrayListAddBeginning(){

        Timeable timeable = new Timeable() {

            List<String> list;
            @Override
            public void setup(int n) {
                list = new ArrayList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0; i<n; i++) {
                    list.add(0, "a string");
                }
            }
        };

        profiler("ArrayList add beginning", timeable);
    }

    private static void profileLinkedListAddEnd() {
        Timeable timeable = new Timeable() {

            List<String> list;
            @Override
            public void setup(int n) {
                list = new LinkedList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0; i<n; i++) {
                    list.add("a string");
                }
            }
        };

        profiler("LinkedList add end", timeable);

    }

    private static void profileLinkedListAddBeginning(){

        Timeable timeable = new Timeable() {

            List<String> list;
            @Override
            public void setup(int n) {
                list = new LinkedList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0; i<n; i++) {
                    list.add(0, "a string");
                }
            }
        };

        profiler("LinkedList add beginning", timeable);
    }

    private static void profiler(final String title, final Timeable timeable) {
        Profiler profiler = new Profiler(title, timeable);

        int startN = 4000;
        int endMillis = 1000;
        XYSeries series = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(series);
    }


}
