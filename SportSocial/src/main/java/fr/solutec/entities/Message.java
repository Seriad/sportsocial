package fr.solutec.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private Date dateSendMessage;
	private String contentMessage;
	@ManyToOne
	private User expediteurMessage;
	
	
	


}
