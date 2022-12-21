package at.java.ex04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Ex04Application {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();
        SpringApplication.run(Ex04Application.class, args);
        for(;;){
            for (int i = 0; i < 100; i++) {
                numbers.add(ThreadLocalRandom.current().nextInt());
            }
            Thread.sleep(1);
        }
    }

}

