package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PostMapping("schedule/modify/{idUser}/{idActivity}")
	public Activity modifyActivity(@RequestBody Activity newActivity, @PathVariable Long idUser, @PathVariable Long idActivity) {
		Activity oldActivity = activityRepo.findById(idActivity).get();
		User user = userRepo.findByIdUser(idUser).get();
		
		newActivity.setIdActivity(oldActivity.getIdActivity());
		newActivity.setSportActivity(oldActivity.getSportActivity());
		
		activityRepo.save(newActivity);
		UserActivity act = new UserActivity(user,newActivity);
		userActivityRepo.save(act);

		return newActivity;
	}
	
	@DeleteMapping("schedule/delete/{idActivity}")
	public Activity deleteActivity (@PathVariable Long idActivity) {
		Activity activity = activityRepo.findById(idActivity).get();
		
		UserActivity userAct = activityRepo.findByIdActivity(idActivity);
	
		userActivityRepo.delete(userAct);
		activityRepo.delete(activity);
		
		return activity;
	}
	

}
