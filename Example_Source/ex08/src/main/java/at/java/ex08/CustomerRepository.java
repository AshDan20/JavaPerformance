package at.java.ex08;

import at.java.ex08.entities.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Customer> findAll() {
        //String queryString = "select c from Customer c";
        String queryString = "select c from Customer c left join fetch c.addresses left join fetch c.salesRep";
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
