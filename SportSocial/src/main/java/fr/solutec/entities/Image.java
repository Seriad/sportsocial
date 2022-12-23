package fr.solutec.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Image implements Serializable {

	@Id @GeneratedValue
	private Long idImage;
	private String nameImage;
}
