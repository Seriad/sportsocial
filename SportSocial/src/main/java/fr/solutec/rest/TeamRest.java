package fr.solutec.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Event;
import fr.solutec.entities.Friend;
import fr.solutec.entities.Message;
import fr.solutec.entities.Messagerie;
import fr.solutec.entities.Team;
import fr.solutec.entities.User;
import fr.solutec.repository.MessageRepository;
import fr.solutec.repository.TeamRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class TeamRest {
	@Autowired
	private TeamRepository teamRepo;
	@Autowired 
	private MessageRepository messageRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("team/{idUser}")
	private List<Team> MyTeams(@PathVariable Long idUser){
		return teamRepo.getMyTeam(idUser);
	}
	
	@GetMapping("team/all/{idTeam}")
	private List<Team> MyTeamsInformation(@PathVariable Long idTeam){
		return teamRepo.getMyTeamInformation(idTeam);
	}
	
	@GetMapping("team/message/{idTeam}")
	private Iterable<Team> MyTeamsMessage(@PathVariable Long idTeam){
		return teamRepo.getMyTeamMessage(idTeam);
		
	}
	
	@PostMapping("team/envoyer/{idTeam}/{idExp}")
    public Message sendTeamMessageById(@PathVariable Long idTeam, @PathVariable Long idExp, @RequestBody Message message) {

        String contenu= message.getContentMessage();
        Optional<User>uexp=userRepo.findByIdUser(idExp);
        Message m = new Message (null,null,contenu,uexp.get());
        Message msave = messageRepo.save(m);

        Optional<Team>team = teamRepo.findById(idTeam);
        team.get().getConversation().add(msave);

        return messageRepo.save(m);
    }
	
    @DeleteMapping("team/quit/{idTeam}/{idUser}") //Quitter un groupe 
    public Team quitTeam(@PathVariable Long idTeam, @PathVariable Long idUser) {
    	Team team = teamRepo.findById(idTeam).get();
    	team.getMembres().removeIf(p-> p.getIdUser()==idUser);
    	return teamRepo.save(team);
    	
    }

       
	
	

}
