package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Address;
import fr.solutec.repository.AddressRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class AddressRest {
	
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("address")
	public Iterable<Address> getAllAddress(){
		return addressRepo.findAll();
	}
	
	@PostMapping("address")
	public Address saveAddress(@RequestBody Address a) {
		return addressRepo.save(a);
	}
	
	
	
	
	
	@PutMapping("address/{id}/{idAddress}")
	public Address ModifyAddress(@RequestBody Address a, @PathVariable Long id,@PathVariable Long idAddress) {
		Address a_old = addressRepo.findByUser(idAddress);
		a_old.setCityAddress(a.getCityAddress());
		a_old.setStreetAddress(a.getStreetAddress());
		a_old.setZipCodeAddress(a.getZipCodeAddress());
		return addressRepo.save(a_old);
	}
}
