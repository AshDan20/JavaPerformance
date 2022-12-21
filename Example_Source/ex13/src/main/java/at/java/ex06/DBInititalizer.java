package at.java.ex06;

import at.java.ex06.entities.Customer;
import at.java.ex06.repository.CustomerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class DBInititalizer{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DBInititalizer.class);
    @Value("${optimized}")
    private boolean optimized;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private List<Customer> allCustomers;

    @EventListener
    public void initDb(ContextRefreshedEvent event){

        long numberOfCustomers = (long)em.createQuery("select count(c.id) from Customer c").getSingleResult();
        if (numberOfCustomers>0){
            log.info("Database already initialized");
        } else{
            log.info("Optimized: " + optimized);
            log.info("Starting DB initialization.");
            if (optimized) {
                allCustomers.parallelStream().forEach(c -> repo.save(c));
            } else {
                allCustomers.stream().forEach(c -> repo.save(c));
            }
            log.info("Finished DB initialization.");
        }
    }
}
