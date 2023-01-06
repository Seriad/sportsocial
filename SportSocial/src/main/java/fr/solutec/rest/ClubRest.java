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
	
	@GetMapping("mesClubs/{idUser}")
	public Iterable<Club> getMyClubs(@PathVariable Long idUser){
		return clubRepo.getMyClubs(idUser);
	}
	
	@GetMapping("autresClubs/{idUser}")
	public Iterable<Club> getOtherClubs(@PathVariable Long idUser){
		return clubRepo.getOtherClubs(idUser);
	}
	
	
	@PostMapping("club/{idUser}")
	public Club createClub(@PathVariable Long idUser, @RequestBody Club c) {
		Optional<User> user = this.userRepo.findByIdUser(idUser);
		Club clubCreated = c;
		clubCreated.setCreateur(user.get());
		clubRepo.save(clubCreated);
		
		return clubCreated;
	}
	
	@PatchMapping("club/rejoindre/{idUser}/{idClub}")
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
	
	
	//récupérer les amis qui sont dans le même club que nous
	
	@GetMapping("club/amis/{idUser}/{idClub}")
	public Iterable<User> getFriendsInClub(@PathVariable Long idUser, @PathVariable Long idClub){
		return userRepo.getFriendsInClub(idUser, idClub);
	}
	
	// récupérer les non amis qui sont dans le même club que nous
	@GetMapping("club/nonamis/{idUser}/{idClub}")
	public Iterable<User> test(@PathVariable Long idUser, @PathVariable Long idClub){
		return userRepo.getNonFriendsInClub(idUser, idClub);
	}
	
}

