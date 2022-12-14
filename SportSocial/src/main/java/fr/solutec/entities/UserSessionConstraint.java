package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;



@Embeddable
public class UserSessionConstraint implements Serializable{

	
	private Long user;
	private Long session;
}
