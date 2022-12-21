package at.java.ex06;

import at.java.ex06.entities.Address;
import at.java.ex06.entities.Customer;
import at.java.ex06.entities.SalesRep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TestDataConfig {
    @Bean
    public List<Customer> customerList(){
        List<Customer> result = new ArrayList<>();
        for (int i = 0; i < 7000; i++) {
            Address address = Address.builder().city("Vienna").street("Street").build();
            SalesRep salesRep = SalesRep.builder().name("Martin" + i).build();
            List<Address> addresses = Arrays.asList(address);
            Customer customer = Customer.builder()
                    .addresses(addresses)
                    .salesRep(salesRep)
                    .name("Customer" + i)
                    .build();
            result.add(customer);
        }
        return result;
    }
}
