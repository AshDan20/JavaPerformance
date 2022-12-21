package at.java.ex05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Configuration
public class TestDataConfig {
    @Bean
    @Scope("prototype")
    public List<Customer> customers(){
         return LongStream.range(1,100)
                .mapToObj(i->new Customer(i,"Customer"+i))
                .collect(Collectors.toList());
    }
}
