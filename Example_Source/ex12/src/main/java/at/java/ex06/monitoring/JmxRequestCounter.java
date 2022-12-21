package at.java.ex06.monitoring;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@ManagedResource
public class JmxRequestCounter {
    private AtomicLong counter = new AtomicLong(0);

    public void count(){
        counter.incrementAndGet();
    }

    @ManagedAttribute
    public long getNumberOfRequests(){
        return counter.get();
    }

    @ManagedOperation
    public void reset(){
        counter.set(0);
    }
}
