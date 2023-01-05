package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
    @Query("SELECT t FROM Team t INNER JOIN t.membres m WHERE (m.idUser = ?1 )")
    List<Team> getMyTeam (Long idUser);
    
    @Query("SELECT t FROM Team t WHERE t.idTeam = ?1 ")
    List<Team> getMyTeamInformation (Long idTeam);
   
    
    @Query("SELECT c FROM Team t INNER JOIN t.conversation c WHERE (t.idTeam = ?1 ) Order By c.dateSendMessage Asc")
    Iterable<Team> getMyTeamMessage (Long idTeam);
    
    @Query("SELECT c FROM Team t INNER JOIN t.membres c WHERE (t.idTeam = ?1 )")
    Iterable<Team> getMyTeamMember (Long idTeam);
    
    
    @Query("SELECT t FROM Team t ")
    List<Team> getAllTeam ();
    


}
