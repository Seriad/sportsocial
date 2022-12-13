package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data @IdClass(UserSessionPK.class)
public class CoachTrainingSession {

	@Id @GeneratedValue @ManyToOne
	private User userFK;
	@Id @GeneratedValue @ManyToOne
	private Session sessionFK;
	
}
