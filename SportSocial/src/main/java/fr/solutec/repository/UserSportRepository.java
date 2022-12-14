package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserSport;

public interface UserSportRepository extends CrudRepository<UserSport, Long > {
	
	@Query("SELECT u FROM UserSport u WHERE u.sport.nameSport = ?1 AND u.user.coachUser = true")
	public List<UserSport> searchCoachBySport(String nameSport); 

	@Query("SELECT u FROM UserSport u WHERE u.sport.nameSport = ?1 ORDER BY u.score asc")
	public List<UserSport>searchScoreBySportAsc(String nameSport);
	
	@Query("SELECT u FROM UserSport u JOIN Sport s ON u.sport=s.idSport WHERE u.sport.nameSport = ?1 ORDER BY u.score desc")
	public List<UserSport>searchScoreBySportDesc(String nameSport);
}

