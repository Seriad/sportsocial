package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Club;
import fr.solutec.entities.User;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class ClubRest {

	@Autowired
	private ClubRepository clubRepo;
	

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("club")
	public Iterable<Club> getAllClub(){
		return clubRepo.findAll();
	}
	
	@PostMapping("club/{idUser}")
	public Club createClub(@PathVariable Long idUser, @RequestBody Club c) {
		Optional<User> user = this.userRepo.findByIdUser(idUser);
		Club clubCreated = c;
		clubCreated.setCreateur(user.get());
		clubRepo.save(clubCreated);
		
		return clubCreated;
	}
	
	@PatchMapping("club/participer/{idUser}/{idClub}")
	public Club addUserToClub(@PathVariable Long idUser, @PathVariable Long idClub) {
		Optional<User> u = userRepo.findById(idUser);
		Optional<Club> c = clubRepo.findById(idClub);
		c.get().getMembres().add(u.get());
		return clubRepo.save(c.get());
	}
	
	@PatchMapping("club/desister/{idUser}/{idClub}")
	public Club removeUserFromClub(@PathVariable Long idUser, @PathVariable Long idClub) {
		Club c = clubRepo.findById(idClub).get();
		c.getMembres().removeIf(u -> u.getIdUser()==idUser);
		return clubRepo.save(c);
	}
	
	
}

