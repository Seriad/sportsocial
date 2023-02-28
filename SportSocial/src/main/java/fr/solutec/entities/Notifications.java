package fr.solutec.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Notifications {

	@Id
	@GeneratedValue
	private Long idNotification;
	private String contenu;
	@ManyToOne
	private User envoyeur;
	@ManyToOne
	private User destinataire;
	private Boolean lu;
}
