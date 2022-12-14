package fr.solutec.rest;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Message;
import fr.solutec.entities.Messagerie;
import fr.solutec.entities.User;
import fr.solutec.repository.MessageRepository;
import fr.solutec.repository.MessagerieRepository;

import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class MessagerieRest {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MessageRepository messageRepo;
	@Autowired 
	private MessagerieRepository messagerieRepo;
	
	// Envoi de message
	

	@PostMapping("message/envoyer")
	public Messagerie sendMessage( @RequestBody Messagerie messagerie) {
		
		String contenu= messagerie.getMessageFK().getContentMessage(); 
		Optional<User>uexp=userRepo.findByLoginUser(messagerie.getMessageFK().getExpediteurMessage().getLoginUser());
		Message m = new Message(null, null, contenu,uexp.get());
		
		Message msave = messageRepo.save(m);
		
		String destinataire=messagerie.getUserFK().getLoginUser(); // récupération login du destinataire
		Optional<User>udest=userRepo.findByLoginUser(destinataire); // récupération du user
		Messagerie um= new Messagerie(udest.get(),msave);
		
		return messagerieRepo.save(um);
		
	} 
}
