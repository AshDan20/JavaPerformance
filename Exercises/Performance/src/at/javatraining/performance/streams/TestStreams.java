package at.javatraining.performance.streams;

import at.javatraining.performance.TimeMeasurement;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class TestStreams {
    public static void main(String[] args) {
        final int ITERATIONS_ADD = 100_000_000;

        int[] numbers = new int[ITERATIONS_ADD];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt();
        }

        TimeMeasurement.measure(5, "Findmax using loop", () -> {
            int max = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] > max) max = numbers[i];
            }
        });

        TimeMeasurement.measure(5, "Findmax using stream variant 1", () -> {
            int max = Arrays.stream(numbers)
                    .max().getAsInt();
        });

        TimeMeasurement.measure(5, "Findmax using stream variant 1 parallel", () -> {
            int max = Arrays.stream(numbers).parallel()
                    .max().getAsInt();
        });


        TimeMeasurement.measure(5, "Findmax using stream variant 2", () -> {
            int[] max = {numbers[0]};
            Arrays.stream(numbers).forEach(number -> {
                if (number > max[0]) max[0] = number;
            });
            System.out.println(max[0]);

            // -> MAX(34 5 6 -32 43)                       -> MAX(22 9 5  2 2 5 7)
            //        43                                                22
            // MAX(43, 22) -> 43
        });

        TimeMeasurement.measure(5, "Findmax using stream variant 3", () -> {
            AtomicInteger max = new AtomicInteger(numbers[0]);
            Arrays.stream(numbers).parallel().forEach(number -> {
                max.getAndUpdate(currentMax -> number > currentMax ? number : currentMax);
            });
            System.out.println(max);

        });

    }
}
