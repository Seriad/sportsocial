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


	



}