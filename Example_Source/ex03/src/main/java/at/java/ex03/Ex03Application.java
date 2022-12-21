package at.java.ex03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex03Application implements CommandLineRunner {
    @Value("${optimized}")
    private boolean optimized;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Ex03Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Optimized: " + optimized);
        Object singleLock = new Object();

        Resource resourceA = new Resource();
        Resource resourceB = new Resource();
        resourceA.setResource(resourceB);
        resourceB.setResource(resourceA);

        if (optimized){
            resourceA.setLock(singleLock);
            resourceB.setLock(singleLock);
        }

        new Thread(resourceA, "ResourceA").start();
        new Thread(resourceB, "ResourceB").start();
        for (;;){
            Thread.sleep(1000);
            System.out.println("Running ...");
        }
    }
}

