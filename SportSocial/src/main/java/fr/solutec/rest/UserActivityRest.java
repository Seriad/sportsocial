
package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.UserActivity;
import fr.solutec.repository.UserActivityRepository;

@RestController
@CrossOrigin("*")
public class UserActivityRest {
@Autowired
private UserActivityRepository userActivityRepository;




@GetMapping("activity/{iduser}")
public List<UserActivity> getUserActivity (@PathVariable Long iduser) {
	return userActivityRepository.findByUserIdUser(iduser);
}

@GetMapping("activity/search/{iduser}/{nameActivity}")
public List<UserActivity> searchActivity (@PathVariable Long iduser, @PathVariable String nameActivity) {
	return userActivityRepository.searchMyActivity(iduser, nameActivity);
}


}
