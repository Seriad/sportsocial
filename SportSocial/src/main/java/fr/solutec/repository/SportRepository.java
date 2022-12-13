package fr.solutec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Sport;
import fr.solutec.entities.User;


public interface SportRepository extends CrudRepository<Sport, Long>{

	List<User> findUserByNameSport(String nameSport);
}
