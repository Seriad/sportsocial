package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Address;
import fr.solutec.repository.AddressRepository;

@RestController @CrossOrigin("*")
public class AddressRest {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@GetMapping("address")
	public Iterable<Address> getAllAddress(){
		return addressRepo.findAll();
	}
	
	@PostMapping("address")
	public Address saveAddress(@RequestBody Address a) {
		return addressRepo.save(a);
	}

}
