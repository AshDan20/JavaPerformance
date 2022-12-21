package at.java.ex06.eventlistener;

import at.java.ex06.entities.SalesRep;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SalesRepRepository {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SalesRepRepository.class);
    @PersistenceContext
    private EntityManager em;

    public SalesRep findById(Long id){
        return em.find(SalesRep.class, id);
    }

    @EventListener
    @Transactional
    public void salesRepChanged(SalesRepChangedEvent event){
        log.info("Starting to save " + event.getSalesRep());
        em.merge(event.getSalesRep());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Finished saving " + event.getSalesRep());
    }
}
