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
public class Message {
	
	@Id @GeneratedValue
	private Long idMessage;
	@CreationTimestamp
	private Timestamp dateSendMessage;
	private Boolean lu;
	private String contentMessage;
	@ManyToOne
	private User expediteurMessage;
	
	
	


}
