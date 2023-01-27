package fr.solutec.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserSport;

public interface UserSportRepository extends CrudRepository<UserSport, Long > {
	
	@Query("SELECT u FROM UserSport u WHERE u.sport.nameSport = ?1 ORDER BY u.score asc")
	public List<UserSport>searchScoreBySportAsc(String nameSport);
	
	@Query("SELECT u FROM UserSport u JOIN Sport s ON u.sport=s.idSport WHERE u.sport.nameSport = ?1 ORDER BY u.score desc")
	public List<UserSport>searchScoreBySportDesc(String nameSport);
	
	@Query("SELECT u.user, SUM(u.score) FROM UserSport u GROUP BY u.user.idUser")
	public List<Object> sumScoreByUser();
	/*
	public List<List<Long>> sumScoreByUser();*/
	
	@Query("SELECT new map (SUM(u.score) AS sum, us AS user) FROM UserSport u INNER JOIN User us ON (us.idUser = u.user.idUser) GROUP BY u.user.idUser ORDER BY SUM(u.score) desc")
	public List<?> sumScore();
	
	@Query("SELECT u FROM UserSport u JOIN Sport s ON u.sport=s.idSport WHERE u.user.idUser =?1 AND s.nameSport LIKE %?2% ")
	public List<UserSport> searchMyScore (Long idUser, String nameSport);

	public java.util.Optional<UserSport> findByUserLoginUser(UserSport sport);

	public List<UserSport> findByUserIdUser(Long id);
}

