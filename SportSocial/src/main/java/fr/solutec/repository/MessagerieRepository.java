package fr.solutec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;import fr.solutec.entities.Messagerie;

public interface MessagerieRepository extends CrudRepository<Messagerie, Long>{

	List<Messagerie> findByDestinataireIdUser(Long idUser);




}
