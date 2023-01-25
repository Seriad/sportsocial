package fr.solutec.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Sport;
import fr.solutec.entities.Training;
import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;
import fr.solutec.repository.UserRepository;
import fr.solutec.repository.UserSportRepository;

@RestController @CrossOrigin("*")
public class UserSportRest {

	@Autowired
	private UserSportRepository userSportRepo;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("classement/{nameSport}/asc")
	public List<UserSport> rankingBySportasc(@PathVariable String nameSport){
		return userSportRepo.searchScoreBySportAsc(nameSport);
	}
	@GetMapping("classement/{nameSport}/desc")
	public List<UserSport> rankingBySportdesc(@PathVariable String nameSport){
		return userSportRepo.searchScoreBySportDesc(nameSport);
	}
	@GetMapping("classement/me/{iduser}")
	public List<UserSport>getUser(@PathVariable Long iduser){
		return userSportRepo.findByUserIdUser(iduser);
	}
	
	@PostMapping("classement/add/{idUser}")
		public UserSport addRanking(@PathVariable Long idUser, @RequestBody UserSport usersport) {
		
		User user = userRepo.findUserById(idUser);
		
		int newScore = usersport.getScore();
		Sport addSport = usersport.getSport();
		
		UserSport newRanking = new UserSport();
		
		newRanking.setScore(newScore);
		newRanking.setSport(addSport);
		newRanking.setUser(user);
		
		return userSportRepo.save(usersport);
	}

	@GetMapping("classement/search/{idUser}/{nameSport}")
		public List<UserSport> searchMyScore (@PathVariable Long idUser, @PathVariable String nameSport) {
		return userSportRepo.searchMyScore(idUser, nameSport);
	}
	
}
