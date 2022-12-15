package fr.solutec.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Sport;
import fr.solutec.entities.User;

public interface SportRepository extends CrudRepository<Sport, Long> {

	
	public Optional<Sport> findByNameSport(String nameSport);
}
