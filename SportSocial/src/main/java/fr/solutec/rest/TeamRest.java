package fr.solutec.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Friend;
import fr.solutec.entities.Team;
import fr.solutec.entities.User;
import fr.solutec.repository.TeamRepository;

@RestController @CrossOrigin("*")
public class TeamRest {
	@Autowired
	private TeamRepository teamRepo;
	
	@GetMapping("team/{idUser}")
	private List<Team> MyTeams(@PathVariable Long idUser){
		return teamRepo.getMyTeam(idUser);
	}
	

}
