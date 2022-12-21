package at.java.ex06.repository;

import at.java.ex06.entities.Address;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.Future;

@Component
public class AsyncAddressRepository {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AsyncAddressRepository.class);
    @PersistenceContext
    private EntityManager em;

    @Async
    @Transactional
    public void saveAddress(Address address){
        log.info("Saving address " + address);
        em.merge(address);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Saving finished");
    }

    @Async
    public Future<Address> findById(Long id){
        return new AsyncResult<>(em.find(Address.class, id));
    }
}
