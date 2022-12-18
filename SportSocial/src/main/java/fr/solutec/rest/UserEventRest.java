package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Event;
import fr.solutec.entities.User;
import fr.solutec.entities.UserEvent;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.UserEventRepository;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserEventRest {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private UserEventRepository userEventRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@PostMapping("userevent/ajouter/{idUser}/{idEvent}")
	public UserEvent addEventToUser(@PathVariable Long idUser, @PathVariable Long idEvent) {
		
		User u = userRepo.findById(idUser).get();
		Event event = eventRepo.findById(idEvent).get();
		UserEvent userEvent = new UserEvent(u, event);
		
		return userEventRepo.save(userEvent);
	}
	
	@GetMapping("userevent/find/{idUser}/{idEvent}")
	public UserEvent findUserEvent(@PathVariable Long idUser, @PathVariable Long idEvent) {
		UserEvent userEvent = userEventRepo.findByUserIdUserAndEventIdEvent(idUser, idEvent).get();
		return userEvent;
	}
	
	@DeleteMapping("userevent/delete/{idUser}/{idEvent}")
	public void deleteUserEvent(@PathVariable Long idUser, @PathVariable Long idEvent) {
		UserEvent userEvent = userEventRepo.findByUserIdUserAndEventIdEvent(idUser, idEvent).get();
		userEventRepo.delete(userEvent);
	}
	
	@GetMapping("userevent")
	public Iterable<UserEvent> getAllUserEvent() {
		return userEventRepo.findAll();
	}

}
