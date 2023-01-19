package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Activity;
import fr.solutec.entities.User;
import fr.solutec.entities.UserActivity;
import fr.solutec.repository.ActivityRepository;
import fr.solutec.repository.UserActivityRepository;
import fr.solutec.repository.UserRepository;


@RestController
@CrossOrigin("*")
public class ActivityRest {
	
	@Autowired
	private ActivityRepository activityRepo;
	@Autowired
	private UserActivityRepository userActivityRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	@PostMapping("schedule/{idUser}")
	public Activity saveActivity(@RequestBody Activity a, @PathVariable Long idUser) {
		User user = userRepo.findByIdUser(idUser).get();
		activityRepo.save(a);
		
		UserActivity act = new UserActivity(user,a);
		userActivityRepo.save(act);

		return a;
	}
	

}
