package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Club;
import fr.solutec.entities.User;

public interface ClubRepository extends CrudRepository<Club, Long>{
	
	@Query("SELECT c FROM Club c INNER JOIN c.membres cm WHERE cm.idUser = ?1 ORDER BY c.titleClub ASC")
	List<Club> getMyClubs(Long idUser);
	
	@Query("SELECT c FROM Club c WHERE c.idClub NOT IN (SELECT c.idClub FROM Club c INNER JOIN c.membres cm WHERE cm.idUser = ?1)ORDER BY c.titleClub ASC ")
	List<Club> getOtherClubs(Long idUser);
	
	@Query("SELECT c FROM Club c WHERE c.idClub NOT IN (SELECT c.idClub FROM Club c INNER JOIN c.membres cm WHERE cm.idUser = ?1) AND (c.titleClub LIKE %?2%) ORDER BY c.titleClub ASC ")
	List<Club> searchOtherClubs(Long idUser, String titleClub);
	
	@Query("SELECT c FROM Club c INNER JOIN c.sportClub cs WHERE c.idClub NOT IN (SELECT c.idClub FROM Club c INNER JOIN c.membres cm WHERE cm.idUser = ?1) AND (cs.nameSport = ?2) ORDER BY c.titleClub ASC ")
	List<Club> filterOtherClubs(Long idUser, String nameSport);

	@Query("SELECT ca FROM Club c INNER JOIN c.admin ca WHERE c.idClub = ?1")
	List<User> getAdmin(Long idClub);

	@Query("SELECT cm FROM Club c INNER JOIN c.membres cm WHERE cm.idUser NOT IN (SELECT ca.idUser FROM Club c INNER JOIN c.admin ca WHERE c.idClub = ?1) AND c.idClub = ?1")
	List<User> getMembresWithoutAdmin(Long idClub);

} 