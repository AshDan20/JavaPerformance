package at.java.ex05;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class BookingsService {
    public void calculcateRevenue(Customer customer) {
        double revenue = ThreadLocalRandom.current().nextDouble(0., 100_000);
        customer.setRevenue(revenue);
        double result = 0;
        for (long i = 0; i < 300_000; i++) {
            result = result + Math.sin(i + 1);
        }
        customer.setRevenue(revenue + result);
    }
}
