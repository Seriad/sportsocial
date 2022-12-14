package fr.solutec.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Session {
	
	@Id @GeneratedValue
	private Long idSession;
	private Timestamp dateStart;
	private Timestamp dateEnd;
	@ManyToOne
	private Sport sportSession;
	@ManyToOne
	private User userSession;

}
