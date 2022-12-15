package fr.solutec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;import fr.solutec.entities.Messagerie;

public interface MessagerieRepository extends CrudRepository<Messagerie, Long>{

	List<Messagerie> findByDestinataireIdUser(Long idUser);

	List<Messagerie> findByMessageExpediteurMessageIdUser(Long id);

	
	@Query("SELECT m FROM Messagerie Where destinataire.idUser=?1 Order By message.dateSendMessage Asc ")
	List<Messagerie> TrouverByDestinataireIdUserAsc(Long idUser);


}
