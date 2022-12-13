package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

}
