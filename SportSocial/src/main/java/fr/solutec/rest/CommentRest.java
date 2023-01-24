package fr.solutec.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Comment;
import fr.solutec.entities.Post;
import fr.solutec.entities.User;
import fr.solutec.repository.CommentRepository;
import fr.solutec.repository.PostRepository;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class CommentRest {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
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
	
	@PostMapping("/post/comment/{idUser}/{idPost}")
	public Comment createCommentInPost(@PathVariable Long idUser, @PathVariable Long idPost, @RequestBody String contenu) {
		Optional<User> user = userRepo.findById(idUser);
		Optional<Post> post = postRepo.findById(idPost);
		Comment c = new Comment(null, null, contenu, user.get(), null, null, 0, 0);
		post.get().getCommentsPost().add(c);
		return commentRepo.save(c);
	}
	
	@PostMapping("/comment/comment/{idUser}/{idComment}")
	public Comment createCommentInComment(@PathVariable Long idUser, @PathVariable Long idComment, @RequestBody String contenu) {
		Optional<User> user = userRepo.findById(idUser);
		Optional<Comment> comment = commentRepo.findById(idComment);
		Comment c = new Comment(null, null, contenu, user.get(), null, null, 0, 0);
		comment.get().getComments().add(c);
		return commentRepo.save(c);
	}
	
	public Boolean checkLike(@PathVariable Long idComment, @PathVariable Long idUser) {
		Optional<Comment> c = commentRepo.findById(idComment);
		List<User> likeComment = c.get().getLikeComments();
		Boolean check= false;
		for (User user : likeComment) {
			if (user.getIdUser() == idUser) {
				check = true;
			}
		}
		return check;
	}
	
	
	@PatchMapping("comment/like/{idUser}/{idComment}")
	public Comment likeComment(@PathVariable Long idComment, @PathVariable Long idUser) {
		Optional<User> u = userRepo.findById(idUser);
		Optional<Comment> c = commentRepo.findById(idComment);
		boolean check = checkLike(idComment, idUser);
		if (check == false) {
			c.get().getLikeComments().add(u.get());	
		}
		return commentRepo.save(c.get());
	}
	
	@PatchMapping("comment/unlike/{idUser}/{idComment}")
	public Comment unlikeComment(@PathVariable Long idComment, @PathVariable Long idUser){
		Optional<Comment> c = commentRepo.findById(idComment);
		c.get().getLikeComments().removeIf(u -> u.getIdUser()==idUser);
		return commentRepo.save(c.get());
	}
	
}
