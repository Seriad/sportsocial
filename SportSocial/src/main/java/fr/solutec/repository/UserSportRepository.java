package fr.solutec.repository;

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
	
	@Query("SELECT u FROM UserSport u JOIN Sport s ON u.sport=s.idSport WHERE u.user.idUser =?1 AND s.nameSport LIKE %?2% ")
	public List<UserSport> searchMyScore (Long idUser, String nameSport);

	public java.util.Optional<UserSport> findByUserLoginUser(UserSport sport);

	public List<UserSport> findByUserIdUser(Long id);
}

