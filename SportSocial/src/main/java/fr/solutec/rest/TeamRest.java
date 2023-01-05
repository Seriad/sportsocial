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
import fr.solutec.repository.ImageRepository;
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
	@Autowired
	private ImageRepository imageRepo;
	
	
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
	
	@GetMapping("team/member/{idTeam}")
	private Iterable<Team> MyTeamsMember(@PathVariable Long idTeam){
		return teamRepo.getMyTeamMember(idTeam);
		
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
    
    @PostMapping("team/rename/{idTeam}")// renomer le groupe 
    public Team renameTeam (@PathVariable Long idTeam, @RequestBody Team team) {
    	String newtitle = team.getTitle();
    	Team t = teamRepo.findById(idTeam).get();
    	t.setTitle(newtitle);
    	return teamRepo.save(t);
    }
    
    @PostMapping("team/addmember/{idTeam}/{idUser}")// Ajouter un membre au groupe 
    public Team addMember (@PathVariable Long idTeam, @PathVariable Long idUser) {
    	User mem= userRepo.findByIdUser(idUser).get();
    	Team t = teamRepo.findById(idTeam).get();
    	t.getMembres().add(mem);
    
    	return teamRepo.save(t);
    			
    }
    
    // Voir les personnes non membres de la team 
    @GetMapping("team/nonmember/{idTeam}") 
	public List<User> userNonTeamMember(@PathVariable Long idTeam){
		return userRepo.getNonTeamMember(idTeam);
	}
    

	@PostMapping("team/create/{idUser}")//Créer un groupe 
	public Team createTeam(@PathVariable Long idUser, @RequestBody Team t) {
		Team teamcreated = teamRepo.save(t);
		User newuser = userRepo.findById(idUser).get();

		teamcreated.getConversation();
		teamcreated.getMembres().add(newuser);
		teamcreated.setImageTeam(imageRepo.findByNameImage("https://img.freepik.com/vecteurs-libre/employes-donnant-mains-aidant-leurs-collegues-monter-escaliers_74855-5236.jpg?w=1060&t=st=1672913654~exp=1672914254~hmac=5575146b381963534bad888d4b17ba15718e7fd0cfa859afcc31555559ecfaa0").get());
		teamcreated.setAdmin(userRepo.findById(idUser).get());
		teamRepo.save(teamcreated);
		return teamcreated;

	}
	
    @DeleteMapping("team/delete/{idTeam}") //Supprimer un groupe 
    public List<Team> quitTeam(@PathVariable Long idTeam) {
    	Team team = teamRepo.findById(idTeam).get();
   
    	teamRepo.delete(team);

    	return teamRepo.getAllTeam();
    	
    }
    
    @GetMapping("team/search/{title}/{idUser}")
    public List<Team> searchMyTeam (@PathVariable Long idUser, @PathVariable String title) {
    	List<Team> team = teamRepo.searchMyTeam(idUser, title);
    	return team;
    			}
	
    
    
    }

       
	
	


