package fr.solutec.rest;


import java.util.Optional;

import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.solutec.entities.Club;
import fr.solutec.entities.Notifications;
import fr.solutec.entities.User;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.NotificationsRepository;
import fr.solutec.repository.UserRepository;

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
		
		Notifications notif = new Notifications(null,admin.get().getLoginUser()+" vous a ajouté comme administrateur du club " + club.get().getTitleClub(), admin.get(), user.get(), false);
		return notifRepo.save(notif);
	}
}
