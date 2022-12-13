package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
