package fr.solutec.entities;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
