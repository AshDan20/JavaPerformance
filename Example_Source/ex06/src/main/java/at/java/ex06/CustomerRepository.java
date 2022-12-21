package at.java.ex06;

import at.java.ex06.entities.Customer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomerRepository.class);
    @Value("${optimized}")
    private boolean optimized;

    @PersistenceContext
    private EntityManager em;

    public List<Customer> findAll() {
        String queryString;
        if (optimized) {
            queryString = "select c from Customer c left join fetch c.addresses left join fetch c.salesRep";
        } else {
            queryString = "select c from Customer c";
        }

        return em.createQuery(queryString, Customer.class)
                .getResultList();
    }

    public Customer save(Customer customer) {
        return em.merge(customer);
    }

    public void saveAll(List<Customer> customers) {
        customers.forEach(c -> em.merge(c));
    }
}
