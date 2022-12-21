package at.java.ex05;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customers")
@RestController
public class CustomerService {
	private Logger log = LoggerFactory.getLogger(CustomerService.class);
	
    @Autowired
    private List<Customer> customers;

    @Autowired
    private BookingsService bookingsService;

    @GetMapping
    public List<Customer> findAllCustomers(){
        //log.info("Incoming Request");
        List<Customer> customers = getCustomers();
        customers.forEach(c->bookingsService.calculcateRevenue(c));
        //log.info("Now Respronding");
        return customers;
    }

    public List<Customer> getCustomers(){
        return new ArrayList<>(customers);
    }
}
