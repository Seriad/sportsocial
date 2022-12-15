package fr.solutec.rest;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Schedule;
import fr.solutec.entities.User;
import fr.solutec.repository.ScheduleRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class ScheduleRest {
	@Autowired
	private ScheduleRepository scheduleRepo;
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("schedule/{idUser}")//création d'un schedule
	private Schedule addSchedule(@RequestBody Schedule s, @PathVariable Long idUser) {
		Optional<User> u = userRepo.findById(idUser);
		s.setUserSchedule(u.get());
		return scheduleRepo.save(s);
	}
	
	@GetMapping("schedule/{idUser}")
	private List<Schedule> getSchedule(@PathVariable Long idUser ){
		Optional<User> u = userRepo.findById(idUser);
		return scheduleRepo.findByUserSchedule(u);
		
	}
	
	@DeleteMapping("schedule/delete/{idSchedule}")//Supression d'un schedule
	private boolean deleteSchedule(@PathVariable Long idSchedule) {
		Optional<Schedule> s = scheduleRepo.findById(idSchedule);
		if (s.isPresent()) {
			scheduleRepo.delete(s.get());
			return true;
		}else {
			return false;
		}
	}
	
	@GetMapping("schedule/{idUser}/{nameActivity}")//Recherche dans le schedule par l'activité
	private List<Schedule> getScheduleWithActivity(@PathVariable Long idUser,@PathVariable String nameActivity) {
		List<Schedule> sched = new ArrayList<>();
		Optional<User> u = userRepo.findById(idUser);
		List<Schedule> schedules = scheduleRepo.findByUserSchedule(u);
		for (Schedule schedule : schedules) {
			if(schedule.getNameActivity().equals(nameActivity)) {
				sched.add(schedule);		
			}
			return sched;
		}return sched;
	}
	

}
