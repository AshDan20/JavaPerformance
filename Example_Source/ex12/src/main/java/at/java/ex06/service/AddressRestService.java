package at.java.ex06.service;

import at.java.ex06.entities.Address;
import at.java.ex06.repository.AsyncAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/addresses")
public class AddressRestService {
    @Autowired
    private AsyncAddressRepository asyncAddressRepository;

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {
        Future<Address> future = asyncAddressRepository.findById(id);
        return future.get();
    }

    @PostMapping
    public void saveAddress(@RequestBody Address address){
        asyncAddressRepository.saveAddress(address);
    }
}
