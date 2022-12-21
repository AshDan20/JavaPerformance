package at.java.ex06.service;

import at.java.ex06.entities.Customer;
import at.java.ex06.entities.SalesRep;
import at.java.ex06.monitoring.JmxRequestCounter;
import at.java.ex06.repository.CustomerRepository;
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
    public static final String NUMBER_OF_ADDRESSES_CACHE = "NUMBER_OF_ADDRESSES_CACHE";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomerRestService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JmxRequestCounter counter;

    @Scheduled(fixedRate = 1000L)
    @CacheEvict(NUMBER_OF_ADDRESSES_CACHE)
    public void refreshCache(){
        log.info("Cache refresh");
    }

    @GetMapping
    public List<Customer> findAll(){
        counter.count();
        return customerRepository.findAll();
    }

    @GetMapping("/noaddr")
    @Cacheable(NUMBER_OF_ADDRESSES_CACHE)
    public long getNumberOfAddresses(){
        counter.count();
        log.info("getNumberOfAdresses called");
        List<Customer> all = findAll();
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
