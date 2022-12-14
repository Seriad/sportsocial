package fr.solutec.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Message;
import fr.solutec.entities.Messagerie;


public interface MessageRepository extends CrudRepository<Message, Long>{

	List<Messagerie> findByExpediteurMessage(String expediteur);


}

