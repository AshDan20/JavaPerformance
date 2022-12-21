package at.java.ex06.service;

import at.java.ex06.entities.SalesRep;
import at.java.ex06.eventlistener.SalesRepChangedEvent;
import at.java.ex06.eventlistener.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("salesreps")
public class SalesReprestService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private SalesRepRepository salesRepRepository;


    @GetMapping("/{id}")
    public SalesRep findById(@PathVariable("id") Long id){
        return salesRepRepository.findById(id);
    }

    @PostMapping
    public void save(@RequestBody SalesRep salesRep){
        eventPublisher.publishEvent(new SalesRepChangedEvent(this, salesRep));
    }
}
