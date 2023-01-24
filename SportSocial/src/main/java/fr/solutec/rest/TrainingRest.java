package fr.solutec.rest;

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

import fr.solutec.entities.Message;
import fr.solutec.entities.Messagerie;
import fr.solutec.entities.Team;
import fr.solutec.entities.Training;
import fr.solutec.entities.User;
import fr.solutec.repository.TrainingRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class TrainingRest {
	@Autowired 
	TrainingRepository trainingRepo;
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping("training/{idUser}")
	private List<Training> MyTraining(@PathVariable Long idUser){
		return trainingRepo.getMyTraining(idUser);

	}
	
	@DeleteMapping("training/delete/{idTraining}")
	private Optional<Training> deleteMyTraining(@PathVariable Long idTraining) {
		trainingRepo.deleteById(idTraining);
		return trainingRepo.findById(idTraining);
	}
	
	
	@PostMapping("training/add/{idUser}")
	public Training createTraining (@PathVariable Long idUser, @RequestBody Training training) {
		
		String newMemo = training.getMemo();

		Training newTraining = new Training();
		newTraining.setMemo(newMemo);
		
		User newUser = userRepo.findUserById(idUser);
		
		newTraining.setAuthor(newUser);
		return trainingRepo.save(newTraining);
	}
	

}
