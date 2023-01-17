package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Comment;
import fr.solutec.entities.Post;
import fr.solutec.repository.CommentRepository;
import fr.solutec.repository.PostRepository;

@RestController
@CrossOrigin("*")
public class PostRest {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	
	@GetMapping("club/posts/{idClub}")
	public Iterable<Post> getPostsInClub(@PathVariable Long idClub){
		Iterable<Post> Posts = postRepo.getPostClub(idClub);
		for (Post post : Posts) {
			int res = commentRepo.countCommentsFromPost(post.getIdPost());
			List<Comment> recup = commentRepo.getCommentOfPost(post.getIdPost());
			for (Comment comment : recup) {
				res = res + commentRepo.countCommentsOfComment(comment.getIdComment());
			}
			post.setNumberComments(res);
			int numberLikes = postRepo.countLikes(post.getIdPost());
			post.setNumberLikes(numberLikes);
		}
		return Posts;
	}
}
