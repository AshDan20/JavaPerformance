package at.java.ex09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Ex09Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex09Application.class, args);
    }

}

