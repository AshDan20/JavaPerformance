package at.java.ex08;

import at.java.ex08.entities.Customer;
import at.java.ex08.entities.SalesRep;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerRestService {
    public static final String NO_ADDR_CACHE = "noaddrCache";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomerRestService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @CacheEvict(NO_ADDR_CACHE)
    @Scheduled(fixedRate = 1000L)
    public void evict(){
        log.info("evictCache");
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/noaddr")
    @Cacheable(NO_ADDR_CACHE)
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
