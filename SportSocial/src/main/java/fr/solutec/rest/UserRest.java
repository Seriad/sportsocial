package fr.solutec.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Image;
import fr.solutec.entities.Sport;
import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.ImageRepository;
import fr.solutec.repository.UserRepository;
import fr.solutec.repository.UserSportRepository;


@RestController
@CrossOrigin("*")
public class UserRest {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserSportRepository userSportRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private ImageRepository imageRepo;


	@GetMapping("user")
	public Iterable<User> getAllUser() {
		return userRepo.findAll();
	}
	
	@GetMapping("user/exceptconnected/{idUser}")
	public List<User> getAllUserExceptConnected(@PathVariable Long idUser) {
		return userRepo.findAllUserExceptConnected(idUser);
	}

	@PostMapping("user")
	public Optional<User> postByLoginAndPassword(@RequestBody User u) {
		return userRepo.findByLoginUserAndPasswordUser(u.getLoginUser(), u.getPasswordUser() );
	}
	
	@PostMapping("user/save")
	public User saveUser(@RequestBody User u) {
		return userRepo.save(u);
	}

	@GetMapping("user/{id}")
	public Optional<User> getOneUser(@PathVariable Long id) {
		return userRepo.findById(id);
	}

	@DeleteMapping("user/{id}")
	public boolean deleteOneUser(@PathVariable Long id) {
		Optional<User> u = userRepo.findById(id);

		if (u.isPresent()) {
			userRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	
	//modification user
	@PutMapping("user/{id}")
	public User ModifyUser(@RequestBody User u_new, @PathVariable Long id) {
		
		//Securite pour empecher la modification du mot de passe avec ce mapping
		User u_old = userRepo.findById(id).get();
		u_new.setPasswordUser(u_old.getPasswordUser());
		
		u_new.setIdUser(id);
		return userRepo.save(u_new);
	}
	
	//Modification pwd
	@PutMapping("user/password/{id}")
	public User ModifyPwd(@RequestBody User user, @PathVariable Long id) {
		User u = userRepo.findById(id).get();
		u.setPasswordUser(user.getPasswordUser());
		return userRepo.save(u);
	}
	
	@GetMapping("coach")//Cherche les coachs
    public List<User> searchCoachs(Long id) { 
     return userRepo.getCoachsById(id);
     } 

	  

	 @GetMapping("search/{lastname}/{firstname}") //Rechercher une personne avec son nom et prénom
	 public List<User> searchByname(@PathVariable String lastname,@PathVariable String firstname){
	    return userRepo.searchUserByname(lastname, firstname);
	    }
	 
	@GetMapping("coach/{nameSport}") //Chercher les coachs en filtrant par le sport
	public List<User> searchCoachesBySport(@PathVariable String nameSport){
	return userRepo.searchCoachBySport(nameSport);
	}
	
	@GetMapping("user/participateEvent/{idEvent}")//chercher les users participant a un event
	public List<User> usersParticipatingEvent(@PathVariable Long idEvent){
		return eventRepo.findById(idEvent).get().getParticipants();
	}

	
	@GetMapping("user/inventaire/{idUser}")//Voir la liste d'image (Id) de l'utilisateur
	public ArrayList<String> getInventaire(@PathVariable Long idUser){
		Optional<User> u = userRepo.findById(idUser);
		List<String> i = new ArrayList<String>();
		for (Long idImage : u.get().getInventaire()) {
			Optional<Image> img = imageRepo.findById(idImage);
			i.add(img.get().getNameImage());
		}
		return (ArrayList<String>) i;
	}
	
	@GetMapping("user/changement/{idUser}/{idImage}")
	public boolean changementPP(@PathVariable Long idUser, @PathVariable Long idImage) {
		Optional<User> u = userRepo.findById(idUser);
		Optional<Image> i = imageRepo.findById(idImage);
		if(i.isPresent()) {
		u.get().setImageUser(i.get());
		return true;
	}else {
		return false;
	}
		}


	@GetMapping("user/search/{loginUser}")
	public List<User>FilterUser(@PathVariable String loginUser){
		return userRepo.SearchUserByLogin(loginUser);

	}
}
