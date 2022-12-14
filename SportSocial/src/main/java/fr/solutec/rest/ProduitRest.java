package fr.solutec.rest;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Produit;
import fr.solutec.repository.ProduitRepository;

@RestController @CrossOrigin("*")

public class ProduitRest {
	
	private ProduitRepository produitRepo;
	
	@GetMapping("avatar")
	private Iterable<Produit> getAllAvatar() {
		return produitRepo.findAll();
	}
	
	@GetMapping("avatar/{description}")
	private List<Produit> getAvatarWithDescription(@PathVariable String description){
		return produitRepo.getProduitByDescriptif(description);
	}
}
