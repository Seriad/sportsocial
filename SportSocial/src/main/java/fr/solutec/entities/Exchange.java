package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class Exchange {
	@javax.persistence.Id @GeneratedValue
	private Long idExchange;
	@ManyToOne
	private User applicant;
	@ManyToOne
	private User receiver;
	private String notification;
	private int montantToken;

}
