package at.java.ex06.eventlistener;

import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SalesRepChangedEventLogger {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SalesRepChangedEventLogger.class);

    @EventListener
    public void salesRepChanged(SalesRepChangedEvent event){
      log.info("Salesrep Changed: " + event.getSalesRep());
    }
}
