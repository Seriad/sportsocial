package fr.solutec.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	

	@Query("SELECT e FROM Event e WHERE e.dateStart > CURRENT_TIMESTAMP order by e.dateStart asc")
    Iterable<Event> eventsToCome ();

	
	@Query("SELECT e from Event e INNER JOIN UserEvent ue on e.idEvent=ue.event.idEvent WHERE ue.user.idUser = ?1 order by e.dateStart desc")
	Iterable<Event> eventsOfUser(Long idUser);
}
