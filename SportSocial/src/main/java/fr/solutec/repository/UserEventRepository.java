package fr.solutec.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserEvent;

public interface UserEventRepository extends CrudRepository<UserEvent, Long>{
	
	
	
	List<UserEvent> findByUserIdUser (Long idUser);
	
	Optional<UserEvent> findByUserIdUserAndEventIdEvent (Long idUser, Long idEvent);
	
	

}
