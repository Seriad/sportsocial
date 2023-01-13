package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c WHERE c.id IN (SELECT cp.id FROM Post p INNER JOIN p.commentsPost cp WHERE p.id = ?1 ) ORDER BY c.dateCreationComment DESC")
	List<Comment> getCommentOfPost(Long idClub);
	
}
