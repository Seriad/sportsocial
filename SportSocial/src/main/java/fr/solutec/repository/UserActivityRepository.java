package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Activity;
import fr.solutec.entities.UserActivity;

public interface UserActivityRepository extends CrudRepository<UserActivity, Long>{
	
	
	@Query("SELECT ua FROM UserActivity ua INNER JOIN Activity a ON a.idActivity = ua.activity.idActivity WHERE ua.user.idUser=?1 AND a.nameActivity LIKE %?2%")
	public List<UserActivity> searchMyActivity(Long idUser, String nameActivity);
	
	@Query("SELECT ua FROM UserActivity ua INNER JOIN Activity a ON a.idActivity = ua.activity.idActivity WHERE ua.user.idUser=?1 ORDER BY a.dateStart desc")
	public List<UserActivity> findMyActivity(Long idUser);


	List<UserActivity>findByUserIdUser(Long iduser);
	
	
	

}
