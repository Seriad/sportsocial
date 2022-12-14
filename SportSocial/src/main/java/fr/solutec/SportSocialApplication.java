package fr.solutec;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.solutec.entities.Address;
import fr.solutec.entities.Event;
import fr.solutec.entities.Friend;
import fr.solutec.entities.Image;
import fr.solutec.entities.Message;
import fr.solutec.entities.Messagerie;
import fr.solutec.entities.Sport;
import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;
import fr.solutec.repository.AddressRepository;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.FriendRepository;
import fr.solutec.repository.ImageRepository;
import fr.solutec.repository.MessageRepository;
import fr.solutec.repository.MessagerieRepository;
import fr.solutec.repository.SportRepository;
import fr.solutec.repository.UserRepository;
import fr.solutec.repository.UserSportRepository;

@SpringBootApplication
public class SportSocialApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private ImageRepository imageRepo;
	@Autowired
	private SportRepository sportRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private MessageRepository messageRepo;
	@Autowired
	private MessagerieRepository messagerieRepo;
	@Autowired
	private FriendRepository friendRepo;
	@Autowired
	private UserSportRepository userSportRepo;

	public static void main(String[] args) {
		SpringApplication.run(SportSocialApplication.class, args);
		System.out.println("Lancement terminé");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		Address a1 = new Address(null, "2 rue Pomme","75002","Paris");
		Image i1 = new Image(null, "lien image"); //Utilisateur
		User u1 = new User(null, "Valjean", "Jean",Date.valueOf("1870-03-25"),"jeanval","123",false,a1,i1);
		
		Address a2 = new Address(null, "17 avenue des salades","75007","Paris");
		Image i2 = new Image(null, "lien image2");//Utilisateur
		User u2 = new User(null, "Joline", "Cujoh",Date.valueOf("1870-03-25"),"joline","cujoh",false,a2,i2);
		
		Address a3 = new Address(null, "322 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		Image i3 = new Image(null, "lien image3");//Utilisateur
		User u3 = new User(null, "Josuke", "Higashikata",Date.valueOf("1870-03-25"),"josuke","higashikata",false,a3,i3);
		
		Address a10 = new Address(null, "325 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		Image i10 = new Image(null, "lien image10");//Utilisateur
		User u4 = new User(null, "Nekosuke", "PLAITON",Date.valueOf("1980-03-25"),"suke","jsuiscoach",true,a10,i10);
		
		Address a11 = new Address(null, "75 Avenue jean Lolive","93500","Pantin");
		Image i11 = new Image(null, "lien image11");//Utilisateur
		User u5 = new User(null, "Heirosuke", "PLATINI",Date.valueOf("1970-03-25"),"Bolo","jsuiscoachaussi",true,a11,i11);
		
		Address a4 = new Address(null, "73 Rue des sportifs","75008","Paris");
		Address a5 = new Address(null, "25 Rue de Lacretelle","75168","Saint-Maur-des-Fossés");
		Address a6 = new Address(null, "3 Avenue du Général Leclerc","92200","Neuilly-Sur-Seine");
		
		Image i4 = new Image(null, "lien image4");//Sport
		Image i5 = new Image(null, "lien image5");//Sport
		Image i6 = new Image(null, "lien image6");//Sport
		
		Image i7 = new Image(null, "lien image7");//Event
		Image i8 = new Image(null, "lien image8");//Event
		Image i9 = new Image(null, "lien image9");//Event
		
		Sport s1 = new Sport(null,"Course à pied",i4);
		Sport s2 = new Sport(null,"Escalade",i5);
		Sport s3 = new Sport(null,"Piscine",i6);
		
		Event e1 = new Event(null,"Evenement de course à pied!","Bah on va courir hein",Timestamp.valueOf("2022-12-25 10:00:00"),Timestamp.valueOf("2022-12-25 12:00:00"),0,a4,i5,s1);
		Event e2 = new Event(null,"J'adore Grimper","Escalade jusqu'au sommet du MontHugual",Timestamp.valueOf("2022-12-10 08:00:00"),Timestamp.valueOf("2022-12-10 16:00:00"),0,a5,i6,s2);
		Event e3 = new Event(null,"La Piscine c'est pas ouf mais bon","On y va faut bien se muscler un peu",Timestamp.valueOf("2022-12-15 18:30:00"),Timestamp.valueOf("2022-12-15 20:15:00"),20,a5,i6,s3);
		
		Friend f1 = new Friend(null,u1,u2,false);
	    Friend f2 = new Friend(null,u1,u3,false);
	    Friend f3 = new Friend(null,u2,u3,true);
	    
	    UserSport us1 = new UserSport(u1,s1);
	    UserSport us2 = new UserSport(u4,s3);
	    UserSport us3 = new UserSport(u5,s2);
		
		
		addressRepo.save(a1);
		imageRepo.save(i1);
		userRepo.save(u1);
		
		addressRepo.save(a2);
		imageRepo.save(i2);
		userRepo.save(u2);
		
		addressRepo.save(a3);
		imageRepo.save(i3);
		userRepo.save(u3);
		
		addressRepo.save(a10);
		imageRepo.save(i10);
		userRepo.save(u4);
		
		addressRepo.save(a11);
		imageRepo.save(i11);
		userRepo.save(u5);
		
		addressRepo.save(a4);
		addressRepo.save(a5);
		addressRepo.save(a6);
		
		imageRepo.save(i4);
		imageRepo.save(i5);
		imageRepo.save(i6);
		imageRepo.save(i7);
		imageRepo.save(i8);
		imageRepo.save(i9);
		
		sportRepo.save(s1);
		sportRepo.save(s2);
		sportRepo.save(s3);
		
		eventRepo.save(e1);
		eventRepo.save(e2);
		eventRepo.save(e3);
		
		friendRepo.save(f1);
	    friendRepo.save(f2);
	    friendRepo.save(f3);
	    
	    userSportRepo.save(us1);
	    userSportRepo.save(us2);
	    userSportRepo.save(us3);
	    
	    
		
		
	}

}

