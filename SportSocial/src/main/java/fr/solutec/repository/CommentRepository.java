package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c WHERE c.id IN (SELECT cp.id FROM Post p INNER JOIN p.commentsPost cp WHERE p.id = ?1 ) ORDER BY c.dateCreationComment DESC")
	List<Comment> getCommentOfPost(Long idPost);
	
	@Query("SELECT COUNT(*) FROM Post p INNER JOIN p.commentsPost cp WHERE p.id = ?1")
	int countCommentsFromPost (Long idPost);
	
	@Query("SELECT COUNT(*) FROM Comment c INNER JOIN c.comments WHERE c.id = ?1")
	int countCommentsOfComment (Long idComment);
	
	@Query("SELECT cc FROM Comment c INNER JOIN c.comments cc WHERE c.id = ?1")
	List<Comment> getCommentsOfComment(Long idComment);
	
	@Query("SELECT COUNT(*) FROM Comment c INNER JOIN c.likeComments WHERE c.id = ?1")
	int countLikes (Long idComment);
	
}
