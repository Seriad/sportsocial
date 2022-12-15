package fr.solutec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;import fr.solutec.entities.Messagerie;

public interface MessagerieRepository extends CrudRepository<Messagerie, Long>{

	List<Messagerie> findByDestinataireIdUser(Long idUser);

	List<Messagerie> findByMessageExpediteurMessageIdUser(Long id);

	
	@Query("SELECT m FROM Messagerie m Where m.destinataire.idUser=?1 Order By m.message.dateSendMessage Asc ")
	List<Messagerie> TrouverByDestinataireIdUserAsc(Long idUser);

	@Query("SELECT m FROM Messagerie m Where (m.destinataire.idUser=?1 AND m.message.expediteurMessage.idUser=?2) Order By m.message.dateSendMessage Asc ")
	List<Messagerie> TrouverByDestinataireAndByExpediteurIdUserAsc(Long idDest, Long idExp);
	
	@Query("SELECT m FROM Messagerie m Where (m.destinataire.idUser=?1 AND m.message.expediteurMessage.idUser=?2) OR (m.destinataire.idUser=?2 AND m.message.expediteurMessage.idUser=?1) Order By m.message.dateSendMessage Asc ")
	List<Messagerie>TrouverByDestinataireAndByExpediteurIdUserCombine(Long idDest, Long idExp);
}
