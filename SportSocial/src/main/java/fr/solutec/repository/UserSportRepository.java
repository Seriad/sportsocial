package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import fr.solutec.entities.UserSport;

public interface UserSportRepository extends CrudRepository<UserSport, Long > {
	
	@Query("SELECT u FROM UserSport u WHERE u.sportFK.nameSport = ?1 AND u.userFK.coachUser = true")
	public List<UserSport> searchUserBySport(String nameSport);
}
