package fr.solutec.rest;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Event;
import fr.solutec.repository.EventRepository;

@RestController
@CrossOrigin("*")
public class EventRest {
	
	@Autowired
	private EventRepository eventRepo;
	
	
	@PostMapping("event/create")
	public Event createEvent(@RequestBody Event e) {
		return eventRepo.save(e);
	}
	
	@GetMapping("event")
	public Iterable<Event> getAllEvent() {
		return eventRepo.findAll();
	}
	
	Timestamp now= new Timestamp(System.currentTimeMillis());
	

	@GetMapping("event/tocome")
	public Iterable<Event> getAllEventToCome(){
		return eventRepo.eventsToCome ();
	}

}
