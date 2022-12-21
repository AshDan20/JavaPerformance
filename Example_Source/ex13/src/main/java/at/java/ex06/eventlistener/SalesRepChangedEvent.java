package at.java.ex06.eventlistener;

import at.java.ex06.entities.SalesRep;
import org.springframework.context.ApplicationEvent;

public class SalesRepChangedEvent extends ApplicationEvent {
    private SalesRep salesRep;

    public SalesRepChangedEvent(Object source, SalesRep salesRep) {
        super(source);
        this.salesRep = salesRep;
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }
}
