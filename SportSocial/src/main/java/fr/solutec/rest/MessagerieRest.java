package fr.solutec.rest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		String contenu= messagerie.getMessage().getContentMessage(); 
		Optional<User>uexp=userRepo.findByLoginUser(messagerie.getMessage().getExpediteurMessage().getLoginUser());
		Message m = new Message(null, null, contenu,uexp.get());
		
		Message msave = messageRepo.save(m);
		messageRepo.save(m);
		String destinataire=messagerie.getDestinataire().getLoginUser(); // récupération login du destinataire
		Optional<User>udest=userRepo.findByLoginUser(destinataire); // récupération du user
		Messagerie um= new Messagerie(udest.get(),msave);
		
		return messagerieRepo.save(um);
		
	} 
	
	// Voir les messages 
	
	@GetMapping("message/me/{id}")
	 List<Messagerie> getMyMessage (@PathVariable Long id){
		return messagerieRepo.findByDestinataireIdUser(id);
		//return messagerieRepo.findAll();
	}
	
	
	// Voir les messages qu'on a envoyé
	
	@GetMapping("message/wrote/{id}")
	List<Messagerie> getMessageSend(@PathVariable Long id){

		return messagerieRepo.findByMessageExpediteurMessageIdUser(id);
		
	}

}

	