package at.java.ex06;

import at.java.ex06.entities.Customer;
import at.java.ex06.entities.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerRestService {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/noaddr")
    public long getNumberOfAddresses(){
        List<Customer> all = customerRepository.findAll();
        long count = 0;
        for (Customer customer : all) {
            SalesRep rep = customer.getSalesRep();
            if (rep!=null) {
                count += customer.getAddresses().size();
            }
        }
        return count;

    }
}
