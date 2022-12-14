package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;

public interface UserSportRepository extends CrudRepository<UserSport, Long > {
	
	@Query("SELECT u FROM UserSport u WHERE u.sportFK.idSport = ?1")
	public List<User> searchUserBySport(Long idSport);
}
