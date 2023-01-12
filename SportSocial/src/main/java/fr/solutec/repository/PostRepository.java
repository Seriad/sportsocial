package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

	@Query("SELECT p FROM Post p WHERE p.clubPost.id = ?1 ORDER BY p.dateCreationPost DESC")
	List<Post> getPostClub (Long idClub);
	
}
