package fr.solutec.entities;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

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
	@CreationTimestamp
	private Timestamp dateNotification;
	@ManyToOne
	private User envoyeur;
	@ManyToOne
	private User destinataire;
	private Boolean lu;
	@ManyToOne
	private Post post ;
	@ManyToOne
	private Comment commentaire;
	private String type;
}
