package at.java.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Ex01Application implements CommandLineRunner {
    @Value("${optimized}")
    private boolean optimized;

    public static void main(String[] args) {
        SpringApplication.run(Ex01Application.class, args);
    }

    private static Logger logger = LoggerFactory.getLogger(Ex01Application.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running ...");
        for (int i = 0; i < 5; i++) {
            StringConcat();
        }
    }

    private void StringConcat() {
        logger.info("Optimized: "  + optimized);
        long count = 0;
        long start = System.currentTimeMillis();
        while (true) {
            int random = ThreadLocalRandom.current().nextInt();
            count++;
            if (optimized) {
                //GOOD
                logger.debug("A Message {}", random);
            } else {
                //BAD
               logger.debug("concat A Message ".concat(String.valueOf(random)));
               //basically the same as
               //logger.debug("concat A Message " + random);
            }
            if (count % 100_000_000 == 0){
                logger.info("count: " + count);
            }
        }
    }

}

