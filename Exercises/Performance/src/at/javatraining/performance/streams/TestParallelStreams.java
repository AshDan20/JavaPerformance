package at.javatraining.performance.streams;

import at.javatraining.performance.TimeMeasurement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TestParallelStreams {
    public static void main(String[] args) {
        final int ITERATIONS_ADD = 1_000;

        int[] numbers = new int[ITERATIONS_ADD];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt();
        }

        TimeMeasurement.measure(5, "Findmax using stream variant 1", () -> {
            int max = Arrays.stream(numbers)
                    .map(number -> expensiveOp(number))
                    .max().getAsInt();
        });

        TimeMeasurement.measure(5, "Findmax using stream variant 1 parallel", () -> {
            int max = Arrays.stream(numbers).parallel()
                    .map(number -> expensiveOp(number))
                    .max().getAsInt();
        });


    }

    public static int expensiveOp(int x) {
        double result = x;
        for (int i = 0; i < 1000; i++) {
            result = Math.cos(Math.sin(result));
            result = new BigDecimal(result).add(new BigDecimal(result)).doubleValue();
        }

        return (int)result;
    }
}
