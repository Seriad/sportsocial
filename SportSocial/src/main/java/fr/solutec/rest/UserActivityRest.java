package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.UserActivity;
import fr.solutec.entities.UserEvent;
import fr.solutec.repository.UserActivityRepository;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserActivityRest {
@Autowired
private UserActivityRepository userActivityRepository;
@Autowired
private UserRepository userRepo;



@GetMapping("activity/{iduser}")
public List<UserActivity> getUserActivity (@PathVariable Long iduser) {
	return userActivityRepository.findByUserIdUser(iduser);
}


}
