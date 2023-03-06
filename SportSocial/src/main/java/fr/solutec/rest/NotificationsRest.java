package fr.solutec.rest;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Club;
import fr.solutec.entities.Comment;
import fr.solutec.entities.Notifications;
import fr.solutec.entities.User;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.CommentRepository;
import fr.solutec.repository.NotificationsRepository;
import fr.solutec.repository.UserRepository;


@RestController
@CrossOrigin("*")
public class NotificationsRest {

	@Autowired
	private NotificationsRepository notifRepo;
	
	
	
	@GetMapping("notifications/liste/{idUser}")
	public List<Notifications> getNotifications(@PathVariable Long idUser){
		List<Notifications> notifs = notifRepo.findByDestinataireIdUserOrdererByDate(idUser);
		return notifs;
	}
	
	@PatchMapping("notification/lu/{idNotification}")
	public Notifications notifLu(@PathVariable Long idNotification) {
		Optional<Notifications> notif = notifRepo.findById(idNotification);
		notif.get().setLu(true);
		return notifRepo.save(notif.get());
	}
	
}
