package at.java.ex02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

@SpringBootApplication

public class Ex02Application implements CommandLineRunner {
    private static final Logger logger = Logger.getAnonymousLogger();
    @Value("${optimized}")
    private boolean optimized;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(Ex02Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Optimized: " + optimized);
        for (; ; ) {
            long start = System.currentTimeMillis();
            calculateResults();
            System.out.println("duration: " + (System.currentTimeMillis() - start));
        }
    }

    private void calculateResults() throws InterruptedException, ExecutionException {
        List<Future<Integer>> results = new ArrayList<>();
        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Worker task = new Worker(optimized);

        for (int i = 0; i < 10; i++) {
            Future<Integer> f = ex.submit(task);
            results.add(f);
        }
        for (Future<Integer> future : results) {
            future.get();
        }

        ex.shutdown();
    }
}

class Worker implements Callable<Integer> {
    private boolean optimized;

    public Worker(boolean optimized) {
        this.optimized = optimized;
    }

    @Override
    public Integer call() throws Exception {
        if (optimized) {
            return doOptimized();
        } else {
            return doSynchronized();
        }

    }

    private synchronized Integer doSynchronized() {
        int result = ThreadLocalRandom.current().nextInt();
        sleep();
        return result;
    }

    private Integer doOptimized() {
        int result = ThreadLocalRandom.current().nextInt();
        sleep();
        return result;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

