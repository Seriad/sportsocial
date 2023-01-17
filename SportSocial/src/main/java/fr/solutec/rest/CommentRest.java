package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Comment;
import fr.solutec.repository.CommentRepository;

@RestController
@CrossOrigin("*")
public class CommentRest {

	@Autowired
	private CommentRepository commentRepo;
	
	@GetMapping("club/posts/comments/{idPost}")
	public Iterable<Comment> getCommentsInPost(@PathVariable Long idPost){
		Iterable<Comment> Comments = commentRepo.getCommentOfPost(idPost);
		for (Comment comment : Comments) {
			int res = commentRepo.countCommentsOfComment(comment.getIdComment());
			comment.setNumberComments(res);
			int numberLikes = commentRepo.countLikes(comment.getIdComment());
			comment.setNumberLikes(numberLikes);
		}
		
		return Comments;
	}
	
	@GetMapping(("club/posts/comments/comments/{idComment}"))
	public Iterable<Comment> getCommentsInComment(@PathVariable Long idComment){
		Iterable<Comment> Comments = commentRepo.getCommentsOfComment(idComment);
		
		for (Comment comment : Comments) {
			int res = commentRepo.countCommentsOfComment(comment.getIdComment());
			comment.setNumberComments(res);
			int numberLikes = commentRepo.countLikes(comment.getIdComment());
			comment.setNumberLikes(numberLikes);
		}
		
		
		return Comments;
	}
	
}
