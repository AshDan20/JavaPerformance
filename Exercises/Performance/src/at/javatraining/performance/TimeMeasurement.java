package at.javatraining.performance;

public class TimeMeasurement {
    public static long measure(int iterations, String testTitle, Runnable runnable) {

        System.out.println("*".repeat(50));
        System.out.println("Testing " + testTitle);
        System.out.println("*".repeat(50));
        long lastDuration = Long.MAX_VALUE;
        for (int i = 0; i < iterations; i++) {
            System.gc();
            System.out.println("Iteration " + i);
            long start = System.currentTimeMillis();
            runnable.run();
            long duration = System.currentTimeMillis() - start;
            lastDuration = duration;
            System.out.printf("Duration %d ms %n", duration);
        }
        return lastDuration;
    }
}
