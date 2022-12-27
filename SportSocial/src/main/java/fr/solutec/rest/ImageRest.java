package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Image;
import fr.solutec.repository.ImageRepository;

@RestController
@CrossOrigin("*")
public class ImageRest {
	
	@Autowired
	private ImageRepository imageRepo;
	
	@GetMapping("image")
	public Iterable<Image> getAllImage() {
		return imageRepo.findAll();
	}
	

}
