package at.java.ex09;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@ManagedResource
@Component
public class JmxCounter {
    private AtomicLong counter = new AtomicLong();

    public void count(){
        counter.incrementAndGet();
    }

    @ManagedAttribute
    public long getNumberOfRequests(){
        return counter.get();
    }

    @ManagedOperation
    public void reset(){
        counter.set(0L);
    }
}
