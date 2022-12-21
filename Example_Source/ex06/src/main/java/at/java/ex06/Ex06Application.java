package at.java.ex06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Ex06Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex06Application.class, args);
    }

}

