package fr.solutec.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Notifications;

public interface NotificationsRepository extends CrudRepository<Notifications, Long>{

	@Query("SELECT n FROM Notifications n WHERE n.destinataire.idUser=?1 ORDER BY n.dateNotification DESC")
	List<Notifications> findByDestinataireIdUserOrdererByDate(Long idUser);
	
	
}
