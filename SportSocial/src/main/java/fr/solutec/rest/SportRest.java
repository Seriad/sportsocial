package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Sport;
import fr.solutec.repository.SportRepository;

@RestController
@CrossOrigin("*")
public class SportRest {
	
	@Autowired
	private SportRepository sportRepo;
	
	@GetMapping("sport/id/{nameSport}")
	public Long getSportID(@PathVariable String nameSport) {
		Long id=(long) -1;
		Optional<Sport> s = sportRepo.findByNameSport(nameSport);
		if (s.isPresent()){
			return s.get().getIdSport();
		}else {
			return id;
		}	
	}
	
	@GetMapping("sport")
	public Iterable<Sport> getAllSport() {
		return sportRepo.findAll();
	}

}
