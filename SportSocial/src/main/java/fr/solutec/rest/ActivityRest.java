package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Activity;
import fr.solutec.repository.ActivityRepository;


@RestController
@CrossOrigin("*")
public class ActivityRest {
	
	@Autowired
	private ActivityRepository activityRepo;
	
	
	
	
	@PostMapping("schedule")
	public Activity saveActivity(@RequestBody Activity a) {
		return activityRepo.save(a);
	}
	

}
