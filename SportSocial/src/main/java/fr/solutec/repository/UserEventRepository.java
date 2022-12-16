package fr.solutec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserEvent;

public interface UserEventRepository extends CrudRepository<UserEvent, Long>{
	
	
	
	List<UserEvent> findByUserIdUser (Long idUser);

}
