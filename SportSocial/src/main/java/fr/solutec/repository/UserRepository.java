package fr.solutec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Team;
import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public Optional<User> findByLoginUserAndPasswordUser(String login, String password);
	
	@Query("SELECT u FROM User u INNER JOIN UserSport s ON s.user.idUser=u.idUser WHERE s.sport.nameSport = ?1 AND s.user.coachUser = true")
	public List<User> searchCoachBySport(String nameSport); 
	
	@Query("SELECT u FROM User u WHERE u.idUser = ?1")
	public User findUserById (Long idUser);


    @Query ("SELECT u FROM User u WHERE u.lastNameUser = ?1 AND u.firstNameUser = ?2")
    public List<User> searchUserByname(String lastname, String firstname);


	public Optional<User> findByLoginUser(String login);
	
	@Query("SELECT c FROM User c WHERE coachUser = true")
    List<User> getCoachsById(Long id);

 
	public Optional<User> findByIdUser(Long exp);
	
	
	@Query("SELECT u FROM User u WHERE u.idUser != ?1")
	public List<User> findAllUserExceptConnected (Long idUser);
	
	@Query ("SELECT u FROM User u WHERE (u.id NOT IN ((SELECT f.applicant.id FROM Friend f WHERE (f.receiver.id = ?1 AND f.applicant.id != ?1)))AND (u.id NOT IN (SELECT f.receiver.id FROM Friend f WHERE (f.receiver.id != ?1 AND f.applicant.id = ?1)))) AND (u.id != ?1 )")
	List<User> getNonFriends (Long idUser);
	
	@Query ("SELECT u FROM User u WHERE (u.id NOT IN ((SELECT f.applicant.id FROM Friend f WHERE (f.receiver.id = ?1 AND f.applicant.id != ?1)))AND (u.id NOT IN (SELECT f.receiver.id FROM Friend f WHERE (f.receiver.id != ?1 AND f.applicant.id = ?1)))) AND (u.id != ?1 ) AND (u.loginUser LIKE %?2%)")
	List<User> searchNonFriends (Long idUser, String loginUser);
	
	@Query ("SELECT u FROM User u WHERE (u.idUser NOT IN (SELECT c.idUser FROM Team t INNER JOIN t.membres c INNER JOIN User u ON c.idUser = u.idUser WHERE (t.idTeam = ?1)))")
	List<User> getNonTeamMember (Long idTeam);

	@Query ("SELECT u FROM User u WHERE loginUser LIKE %?1%")
	List<User> SearchUserByLogin (String loginUser);

	@Query ("SELECT cm FROM Club c INNER JOIN c.membres cm WHERE c.idClub = ?2 AND cm.idUser != ?1  AND ( cm.idUser NOT IN (SELECT u.idUser FROM User u WHERE (u.idUser NOT IN ((SELECT f.applicant.id FROM Friend f WHERE (f.accept= true AND f.receiver.id = ?1 AND f.applicant.id != ?1)))AND (u.idUser NOT IN (SELECT f.receiver.id FROM Friend f WHERE (f.accept= true AND f.receiver.id != ?1 AND f.applicant.id = ?1)))) AND (u.id != ?1 )))")
	List<User> getFriendsInClub (Long idUser, Long idClub);
	
	@Query ("SELECT cm FROM Club c INNER JOIN c.membres cm WHERE c.idClub = ?2 AND cm.idUser != ?1  AND ( cm.idUser IN (SELECT u.idUser FROM User u WHERE (u.idUser NOT IN ((SELECT f.applicant.id FROM Friend f WHERE (f.receiver.id = ?1 AND f.applicant.id != ?1)))AND (u.idUser NOT IN (SELECT f.receiver.id FROM Friend f WHERE (f.receiver.id != ?1 AND f.applicant.id = ?1)))) AND (u.id != ?1 )))")
	List<User> getNonFriendsInClub (Long idUser, Long idClub);
	
	@Query ("SELECT cm FROM Club c INNER JOIN c.membres cm WHERE c.idClub = ?2 AND cm.idUser != ?1  AND ( cm.idUser NOT IN (SELECT u.idUser FROM User u WHERE (u.idUser NOT IN ((SELECT f.applicant.id FROM Friend f WHERE (f.accept= false AND f.receiver.id = ?1 AND f.applicant.id != ?1)))AND (u.idUser NOT IN (SELECT f.receiver.id FROM Friend f WHERE (f.accept= false AND f.receiver.id != ?1 AND f.applicant.id = ?1)))) AND (u.id != ?1 )))")
	List<User> getAskedFriendsInClub (Long idUser, Long idClub);
	
}