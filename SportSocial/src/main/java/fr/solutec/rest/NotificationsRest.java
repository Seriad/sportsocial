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
import fr.solutec.entities.Notifications;
import fr.solutec.entities.User;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.NotificationsRepository;
import fr.solutec.repository.UserRepository;


@RestController
@CrossOrigin("*")
public class NotificationsRest {

	@Autowired
	private NotificationsRepository notifRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClubRepository clubRepo;
	
	@PostMapping("notification/adminToUser/add/{idAdmin}/{idUser}/{idClub}")
	public Notifications notifAjoutAdmin(@PathVariable Long idAdmin,@PathVariable Long idUser, @PathVariable Long idClub) {
		Optional<User> admin = userRepo.findById(idAdmin);
		Optional<User> user = userRepo.findById(idUser);
		Optional<Club> club = clubRepo.findById(idClub);
		String contenu = admin.get().getLoginUser()+" vous a ajouté comme administrateur du club " + club.get().getTitleClub();
		Notifications notif = new Notifications(null,contenu, null, admin.get(), user.get(), false);
		return notifRepo.save(notif);
	}
	
	@PostMapping("notification/adminToUser/delete/{idUser}/{idClub}")
	public Notifications notifDeletedAdmin(@PathVariable Long idUser, @PathVariable Long idClub) {
		Optional<User> user = userRepo.findById(idUser);
		Optional<Club> club = clubRepo.findById(idClub);
		String contenu = "Vous avez été destitué de votre rôle d'administrateur du club " + club.get().getTitleClub();
		Notifications notif = new Notifications(null,contenu, null, null, user.get(), false);
		return notifRepo.save(notif);
	}
	
	@PostMapping("notification/userToUser/answer/{idSender}/{idReceiver}")
	public Notifications notifAnswerUser(@PathVariable Long idSender, @PathVariable Long idReceiver) {
		Optional<User> sender = userRepo.findById(idSender);
		Optional<User> receiver = userRepo.findById(idReceiver);
		String contenu = sender.get().getLoginUser()+" a répondu à l'un de vos commentaires";
		Notifications notif = new Notifications(null,contenu, null, sender.get(), receiver.get(), false);
		return notifRepo.save(notif);
	}
	
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
