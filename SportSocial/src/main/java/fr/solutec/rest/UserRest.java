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

import fr.solutec.entities.Club;
import fr.solutec.entities.Friend;
import fr.solutec.entities.Image;
import fr.solutec.entities.Sport;
import fr.solutec.entities.Team;
import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;
import fr.solutec.repository.AddressRepository;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.ImageRepository;
import fr.solutec.repository.TeamRepository;
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
	@Autowired 
	AddressRepository addressRepo;
	@Autowired
	private ClubRepository clubRepo;
	@Autowired
	private TeamRepository teamRepo;


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
		
		User newUser = userRepo.save(u);
		newUser.getInventaire().add(imageRepo.findByNameImage("https://media.istockphoto.com/id/1018999828/fr/vectoriel/ic%C3%B4ne-de-profil-avatar-par-d%C3%A9faut-espace-r%C3%A9serv%C3%A9-de-photo-gris.jpg?s=170667a&w=0&k=20&c=yZAkya7Wg7vtsu8FF86k5r_1IrkE6jp4hwl7sf6bXJ0=")
						.get().getIdImage());
		newUser.setImageUser(imageRepo.findByNameImage("https://media.istockphoto.com/id/1018999828/fr/vectoriel/ic%C3%B4ne-de-profil-avatar-par-d%C3%A9faut-espace-r%C3%A9serv%C3%A9-de-photo-gris.jpg?s=170667a&w=0&k=20&c=yZAkya7Wg7vtsu8FF86k5r_1IrkE6jp4hwl7sf6bXJ0=").get());
		newUser.setAddressUser(addressRepo.findByStreetAddress("").get());
		return userRepo.save(newUser);
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
	public ArrayList<Image> getInventaire(@PathVariable Long idUser){
		Optional<User> u = userRepo.findById(idUser);
		List<Image> i = new ArrayList<Image>();
		for (Long idImage : u.get().getInventaire()) {
			Optional<Image> img = imageRepo.findById(idImage);
			i.add(img.get());
		}
		return (ArrayList<Image>) i;
	}
	
	@GetMapping("user/changement/{idUser}/{idImage}")
	public boolean changementPP(@PathVariable Long idUser, @PathVariable Long idImage) {
		Optional<User> u = userRepo.findById(idUser);
		Optional<Image> i = imageRepo.findById(idImage);
		if(i.isPresent()) {
		u.get().setImageUser(i.get());
		userRepo.save(u.get());
		return true;
	}else {
		return false;
	}
		}
	
	@GetMapping("user/search/{loginUser}")
	private List<User>FilterUser(@PathVariable String loginUser){
		List<User> u = userRepo.SearchUserByLogin(loginUser);

		return u;

	}
	
	@GetMapping("nonfriend/search/{loginUser}/{idUser}")
	private List<User>FilterNonFriend(@PathVariable String loginUser, @PathVariable Long idUser){
		List<User> u = userRepo.searchNonFriends(idUser, loginUser);

		return u;

	}
	
	//Delete user
	@PutMapping("user/delete/{idUser}")
	public User deleteUser(@PathVariable Long idUser) {
		User u = userRepo.findById(idUser).get();
		u.setPasswordUser(null);
		
		return userRepo.save(u);
	}

}
