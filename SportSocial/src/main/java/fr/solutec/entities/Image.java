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
	private String nameImage = "https://as2.ftcdn.net/v2/jpg/01/07/43/45/1000_F_107434511_iarF2z88c6Ds6AlgtwotHSAktWCdYOn7.jpg";
}
