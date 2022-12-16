package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.UserEvent;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.UserEventRepository;

@RestController
@CrossOrigin("*")
public class UserEventRest {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private UserEventRepository userEventRepo;
	
	
	@PostMapping("event/ajouter/{id}")
	public UserEvent addEventToUser(@PathVariable Long idUser, @RequestBody UserEvent userEvent) {
		
		return userEvent;
	}
	

}
