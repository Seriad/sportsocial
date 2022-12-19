package fr.solutec.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import fr.solutec.entities.User;
import fr.solutec.entities.UserEvent;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.FriendRepository;
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
	
	@Autowired 
	private FriendRepository friendRepo;
	
	
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
	public List<Event> getEventsOfOneUser(@PathVariable Long idUser){
		return (List<Event>) eventRepo.eventsOfUser(idUser);
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
	
	@PatchMapping("event/participer/{idEvent}")
	public Event addEventToUser(@RequestBody Event event, @PathVariable Long idEvent) {
		List<User> newParticipant = new ArrayList<>();
		newParticipant = event.getParticipants();
		eventRepo.findById(idEvent).get().setParticipants(newParticipant);
		return event;	
	}
	
	@PatchMapping("event/participer2/{idEvent}")
	public Event ajoutEventToUser(@RequestBody Event event, @PathVariable Long idEvent) {
		List<User> newParticipant = new ArrayList<>();
		newParticipant = event.getParticipants();
		eventRepo.findById(idEvent).get().setParticipants(newParticipant);
		return event;	
	}


}
