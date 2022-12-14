package fr.solutec.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor @AllArgsConstructor
@Entity @Data @IdClass(UserSportConstraint.class)
public class UserSport {

	@Id @ManyToOne
	private User userFK;
	@Id @ManyToOne
	private Sport sportFK;
	private int score;
}
