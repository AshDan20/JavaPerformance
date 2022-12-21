package at.java.ex07;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.java.ex07.entities.Customer;
import at.java.ex07.entities.SalesRep;

@RequestMapping("/customers")
@RestController
public class CustomerRestService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerRestService.class);
    @Autowired
    private CustomerRepository customerRepository;

    private volatile List<Customer> customerCache = new ArrayList<>();


    @PostConstruct
    public void init() {
        refreshCache();
    }

    @Scheduled(fixedRate = 1000L)
    public void refreshCache() {
    	logger.info("Cache Refresh");
        List<Customer> customers = customerRepository.findAll();
        customerCache = customers;
    }

    @GetMapping
    public List<Customer> findAll() {
    	//return customerRepository.findAll();
        return customerCache;
    }

    @GetMapping("/noaddr")
    public long getNumberOfAddresses() {
        List<Customer> all = findAll();
        long count = 0;
        for (Customer customer : all) {
            SalesRep rep = customer.getSalesRep();
            if (rep != null) {
                count += customer.getAddresses().size();
            }
        }
        return count;
    }
}
