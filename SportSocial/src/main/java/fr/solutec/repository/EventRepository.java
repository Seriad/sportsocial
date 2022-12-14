package fr.solutec.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	

	@Query("SELECT e FROM Event e WHERE e.dateStart > CURRENT_TIMESTAMP order by e.dateStart asc")
    Iterable<Event> eventsToCome ();

}
