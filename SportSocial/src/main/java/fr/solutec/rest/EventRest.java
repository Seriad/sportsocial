package fr.solutec.rest;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Event;
import fr.solutec.entities.Friend;
import fr.solutec.entities.Team;
import fr.solutec.entities.User;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.FriendRepository;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class EventRest {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private FriendRepository friendRepo;
	
	
	//Ajoute un event dans la table event et dans son ID et l'id de l'user l'ayant créé dans la table UserEvent
	@PostMapping("event/create/{idUser}")
	public Event createEvent(@PathVariable Long idUser, @RequestBody Event e) {
		Event eventCreated = eventRepo.save(e);
		
		return addUserToEvent(idUser, eventCreated.getIdEvent());
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
	public List<Event> getEventsOfOneUser(@PathVariable Long idUser){
		Iterable<Event> events =  eventRepo.eventsOfOneUser(idUser);
		Timestamp date_now = new Timestamp(System.currentTimeMillis());
		for (Event event : events) {
			boolean bool = date_now.after(event.getDateStart());
			if ((bool)) {
			event.setPastDate(true);
			eventRepo.save(event);
			}
			
			
		}
		
		return (List<Event>) eventRepo.eventsOfOneUser(idUser);
	}
	
	@GetMapping("event/search/{idUser}/{titleEvent}")
	public List<Event> getEventsOfOneUser(@PathVariable Long idUser, @PathVariable String titleEvent){
		Iterable<Event> events =  eventRepo.searchEventsOfOneUser(idUser, titleEvent);
		Timestamp date_now = new Timestamp(System.currentTimeMillis());
		for (Event event : events) {
			boolean bool = date_now.after(event.getDateStart());
			if ((bool)) {
			event.setPastDate(true);
			eventRepo.save(event);
			}
			
			
		}
		
		return (List<Event>) eventRepo.searchEventsOfOneUser(idUser, titleEvent);
	}
	
	
	@GetMapping("event/friends/{idUser}")//Les events des amis
	public List<Event> getEventsOfMyFriends(@PathVariable Long idUser){
		
		//recuperer liste d'amis
		List<User> friends = new ArrayList <>();
        List<Friend> recup = friendRepo.getMyFriends(idUser);
        Optional<User> u = userRepo.findById(idUser);
        if(u.isPresent()) {
            for (Friend friend : recup) {
                if(!friend.getApplicant().equals(u.get())) {
                    friends.add(friend.getApplicant());
                }
                if(!friend.getReceiver().equals(u.get())) {
                    friends.add(friend.getReceiver());
                }
            }
        }
        //fin recuperer liste d'amis
        
        List<Event> eventsOfFriends = new ArrayList<>();
        for (User user : friends) {
        	eventsOfFriends.addAll(getEventsOfOneUser(user.getIdUser()));
		}
        //Retirer les doublons de la liste
        List<Event> eventsOfFriendsWithoutDuplicates = eventsOfFriends.stream()
        		 .distinct()
        		 .collect(Collectors.toList());
        
        return eventsOfFriendsWithoutDuplicates;
	}
	
	@PatchMapping("event/participer/{idUser}/{idEvent}")
	public Event addUserToEvent(@PathVariable Long idUser, @PathVariable Long idEvent) {
		Optional<User> u= userRepo.findById(idUser);
		Optional<Event> e =	eventRepo.findById(idEvent);
		e.get().getParticipants().add(u.get());
		
		return eventRepo.save(e.get());	
	}
	
	
	@PatchMapping("event/desister/{idUser}/{idEvent}")
	public Event removeUserFromEvent(@PathVariable Long idUser, @PathVariable Long idEvent) {
		Event e = eventRepo.findById(idEvent).get();
		e.getParticipants().removeIf(p-> p.getIdUser()==idUser);
		return eventRepo.save(e);
		
	}
	
    @GetMapping("event/search/{titleEvent}")
    public Iterable<Event> searchEvent (@PathVariable String titleEvent) {
    	Iterable<Event> event = eventRepo.searchEventsToCome(titleEvent);
    	return event;
    			}
	

}
