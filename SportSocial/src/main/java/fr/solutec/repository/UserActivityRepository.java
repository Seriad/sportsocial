package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Activity;
import fr.solutec.entities.UserActivity;

public interface UserActivityRepository extends CrudRepository<UserActivity, Long>{
	
	/*
	@Query("SELECT a FROM Activity INNER JOIN UserActivity ua INNER JOIN User u WHERE u.idUser=?1")
	public List<UserActivity> getMyFriendsActivity(Long idUser);
*/

	List<UserActivity>findByUserIdUser(Long iduser);
	

}
