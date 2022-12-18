package fr.solutec.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class EventRest {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private UserEventRepository userEventRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	//Ajoute un event dans la table event et dans son ID et l'id de l'user l'ayant créé dans la table UserEvent
	@PostMapping("event/create/{idUser}")
	public Event createEvent(@PathVariable Long idUser, @RequestBody Event e) {
		
		Event eventCreated = eventRepo.save(e);
		User u = userRepo.findById(idUser).get();
		UserEvent userEvent = new UserEvent(u, eventCreated);
		userEventRepo.save(userEvent);
		
		return eventCreated;
	}
	
	@GetMapping("event")
	public Iterable<Event> getAllEvent() {
		return eventRepo.findAll();
	}
	
	

	@GetMapping("event/tocome")
	public Iterable<Event> getAllEventToCome(){
		return eventRepo.eventsToCome ();
	}
	
	@GetMapping("event/{idUser}")
	public Iterable<Event> getEventsOfOneUser(@PathVariable Long idUser){
		return eventRepo.eventsOfUser(idUser);
	}

}
