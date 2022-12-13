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
import fr.solutec.entities.User;
import fr.solutec.repository.MessageRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class MessageRest {
@Autowired
private MessageRepository messageRepo;
@Autowired
private UserRepository userRepo;

//Création de message

@PostMapping("message/créer/{expediteur}")
public Message createMessage(@PathVariable String expediteur,@RequestBody Date dateSend, @RequestBody String contenu) {
	
	Optional<User>uexp= userRepo.findByLoginUser(expediteur);
	Message m = new Message(null, dateSend, contenu,uexp.get());
	return messageRepo.save(m);
}
}
