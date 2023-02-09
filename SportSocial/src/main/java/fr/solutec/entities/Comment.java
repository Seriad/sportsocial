package fr.solutec.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class Comment {

	
	@Id
	@GeneratedValue
	private Long idComment;
	@CreationTimestamp
	private Timestamp dateCreationComment;
	@Column(columnDefinition="TEXT")
	private String contentComment;
	@ManyToOne
	private User createurComment;
	@ManyToOne
	private User destComment;
	@ManyToMany
	private List<User> likeComments = new ArrayList<User>();
	@OneToMany
	private List<Comment> comments = new ArrayList<Comment>();
	private int numberLikes = 0;
	private int numberComments =0;
}
