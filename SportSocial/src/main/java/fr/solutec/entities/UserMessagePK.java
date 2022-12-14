package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserMessagePK implements Serializable{

	private Long userFK;
	private Long messageFK;
	
}
