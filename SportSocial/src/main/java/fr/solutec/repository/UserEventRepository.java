package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserEvent;

public interface UserEventRepository extends CrudRepository<UserEvent, Long>{

}
