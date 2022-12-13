package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;



@Embeddable
public class UserSessionPK implements Serializable{

	
	private User userFK;
	private Session sessionFK;
}
