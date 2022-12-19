package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Club;
import fr.solutec.repository.ClubRepository;

@RestController
@CrossOrigin("*")
public class ClubRest {

	@Autowired
	private ClubRepository clubRepo;
	
	@GetMapping("club")
	public Iterable<Club> getAllClub(){
		return clubRepo.findAll();
	}
	
	
	
}
