package fr.solutec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public Optional<User> findByLoginUserAndPasswordUser(String login, String password);


    @Query ("SELECT u FROM User u WHERE u.lastNameUser = ?1 AND u.firstNameUser = ?2")
    public List<User> searchUserByname(String lastname, String firstname);


	public Optional<User> findByLoginUser(String login);

 
	
	

}