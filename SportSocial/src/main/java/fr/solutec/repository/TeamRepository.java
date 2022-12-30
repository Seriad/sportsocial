package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
    @Query("SELECT t FROM Team t INNER JOIN t.membres m WHERE (m.idUser = ?1 )")
    List<Team> getMyTeam (Long idUser);
    


}
