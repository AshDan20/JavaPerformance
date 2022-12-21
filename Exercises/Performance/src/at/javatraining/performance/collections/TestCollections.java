package at.javatraining.performance.collections;

import at.javatraining.performance.TimeMeasurement;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestCollections {
    public static void main(String[] args) {
        final int ITERATIONS_ADD = 10_000_000;

        int[] numbers = new int[ITERATIONS_ADD];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt();
        }


        TimeMeasurement.measure(5,"Test Linked List add at the end", () -> {
            LinkedList list = new LinkedList();
            for (int i = 0; i < numbers.length; i++) {
                list.add(numbers[i]);
            }
        });

        TimeMeasurement.measure(5,"Test Array List add at the end", () -> {
            ArrayList list = new ArrayList(numbers.length);
            for (int i = 0; i < numbers.length; i++) {
                list.add(numbers[i]);
            }
        });


        TimeMeasurement.measure(5,"Test  Synchronized Array List add at the end", () -> {
            List list = Collections.synchronizedList(new ArrayList(numbers.length));
            for (int i = 0; i < numbers.length; i++) {
                list.add(numbers[i]);
            }
        });


        TimeMeasurement.measure(5,"Test Linked List add at the start", () -> {
            LinkedList list = new LinkedList();
            for (int i = 0; i < numbers.length; i++) {
                list.add(0, numbers[i]);
            }
        });


       /* TimeMeasurement.measure(5,"Test Array List add at the start", () -> {
            ArrayList list = new ArrayList(numbers.length);
            for (int i = 0; i < numbers.length; i++) {
                list.add(0, numbers[i]);
            }
        });*/

        TimeMeasurement.measure(5,"Test HashSet add", () -> {
            HashSet<Integer> hashSet = new HashSet<>(numbers.length);
            for (int i = 0; i < numbers.length; i++) {
                hashSet.add(numbers[i]);
            }
        });

        TimeMeasurement.measure(5,"Test TreeSet add", () -> {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < numbers.length; i++) {
                set.add(numbers[i]);
            }
        });

    }
}
