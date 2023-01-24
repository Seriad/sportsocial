package fr.solutec.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Team;
import fr.solutec.entities.Training;

public interface TrainingRepository extends CrudRepository<Training, Long> {
	
	@Query("SELECT t FROM Training t WHERE t.author.idUser =?1")
	List<Training> getMyTraining (Long idUser);


}
