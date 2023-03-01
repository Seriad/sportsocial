package fr.solutec.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Notifications;

public interface NotificationsRepository extends CrudRepository<Notifications, Long>{

	List<Notifications> findByDestinataireIdUser(Long idUser);
	
	
}
