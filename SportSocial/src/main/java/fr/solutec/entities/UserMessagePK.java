package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserMessagePK implements Serializable{

	private User userFK;
	private Message messageFK;
	
}
