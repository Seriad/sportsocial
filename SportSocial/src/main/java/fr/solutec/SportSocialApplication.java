package fr.solutec;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.solutec.entities.Address;
import fr.solutec.entities.Image;
import fr.solutec.entities.User;
import fr.solutec.repository.AddressRepository;
import fr.solutec.repository.ImageRepository;
import fr.solutec.repository.UserRepository;

@SpringBootApplication
public class SportSocialApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private ImageRepository imageRepo;

	public static void main(String[] args) {
		SpringApplication.run(SportSocialApplication.class, args);
		System.out.println("Lancement terminé");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		Address a1 = new Address(null, "2 rue Pomme","75002","Paris");
		Image i1 = new Image(null, "lien image");
		User u1 = new User(null, "Valjean", "Jean",Date.valueOf("1870-03-25"),"jeanval","123",false,a1,i1);
		
		addressRepo.save(a1);
		imageRepo.save(i1);
		userRepo.save(u1);
	}

}
