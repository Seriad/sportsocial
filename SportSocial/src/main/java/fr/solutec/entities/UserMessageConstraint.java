package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserMessageConstraint implements Serializable{

	private Long destinataire;
	private Long message;
	
}
