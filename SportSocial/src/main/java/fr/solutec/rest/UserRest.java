package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.User;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserRest {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("user")
	public Iterable<User> getAllUser() {
		return userRepo.findAll();
	}

	@PostMapping("user")
	public User saveUser(@RequestBody User u) {
		return userRepo.save(u);
	}

	@GetMapping("user/{id}")
	public Optional<User> getOneUser(@PathVariable Long id) {
		return userRepo.findById(id);
	}

	@DeleteMapping("user/{id}")
	public boolean deleteOneUser(@PathVariable Long id) {
		Optional<User> u = userRepo.findById(id);

		if (u.isPresent()) {
			userRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@PutMapping("user/{id}")
	public User ModifyUser(@RequestBody User u_new, @PathVariable Long id) {
		
		//Securite pour empecher la modification du mot de passe avec ce mapping
		User u_old = userRepo.findById(id).get();
		u_new.setPasswordUser(u_old.getPasswordUser());
		
		u_new.setIdUser(id);
		return userRepo.save(u_new);
	}
	//ajout com

}
