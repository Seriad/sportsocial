
package fr.solutec.rest;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Event;
import fr.solutec.entities.UserActivity;
import fr.solutec.repository.UserActivityRepository;

@RestController
@CrossOrigin("*")
public class UserActivityRest {
@Autowired
private UserActivityRepository userActivityRepo;

	@GetMapping("activity/{iduser}")
	public List<UserActivity> getUserActivity (@PathVariable Long iduser) {
		Iterable<UserActivity> userAct = userActivityRepo.findMyActivity(iduser);
		Timestamp date_now = new Timestamp(System.currentTimeMillis());
		
		for (UserActivity userA : userAct) {
			boolean bool = date_now.after(userA.getActivity().getDateStart());
			if ((bool)) {
			userA.getActivity().setPastDateActivity(true);
			userActivityRepo.save(userA);
			}
		}
		return userActivityRepo.findMyActivity(iduser);
		}
	
	@GetMapping("activity/search/{iduser}/{nameActivity}")
	public List<UserActivity> searchActivity (@PathVariable Long iduser, @PathVariable String nameActivity) {
		return userActivityRepo.searchMyActivity(iduser, nameActivity);
	}


}
