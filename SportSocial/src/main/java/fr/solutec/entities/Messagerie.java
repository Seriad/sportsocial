package fr.solutec.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data @IdClass(UserMessageConstraint.class)

public class Messagerie {

	
	@Id @ManyToOne
	private User destinataire; // Destinataire
	@Id @ManyToOne @OnDelete (action = OnDeleteAction.CASCADE)
	private Message message; 
}
