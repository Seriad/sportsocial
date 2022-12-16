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
import fr.solutec.entities.Produit;
import fr.solutec.entities.Sport;
import fr.solutec.entities.User;
import fr.solutec.entities.UserSport;
import fr.solutec.repository.AddressRepository;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.FriendRepository;
import fr.solutec.repository.ImageRepository;
import fr.solutec.repository.MessageRepository;
import fr.solutec.repository.MessagerieRepository;
import fr.solutec.repository.ProduitRepository;
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
	@Autowired
	private ProduitRepository produitRepo;

	public static void main(String[] args) {
		SpringApplication.run(SportSocialApplication.class, args);
		System.out.println("Lancement terminé");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		Address a1 = new Address(null, "2 rue Pomme","75002","Paris");
		Image i1 = new Image(null, "lien image"); //Utilisateur
		User u1 = new User(null, "Valjean", "Jean",Date.valueOf("1870-03-25"),"jeanval","123",false,a1,i1,0);
		
		Address a2 = new Address(null, "17 avenue des salades","75007","Paris");
		Image i2 = new Image(null, "lien image2");//Utilisateur
		User u2 = new User(null, "Joline", "Cujoh",Date.valueOf("1870-03-25"),"joline","cujoh",false,a2,i2,0);
		
		Address a3 = new Address(null, "322 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		Image i3 = new Image(null, "lien image3");//Utilisateur
		User u3 = new User(null, "Josuke", "Higashikata",Date.valueOf("1870-03-25"),"josuke","higashikata",false,a3,i3,0);
		
		Address a10 = new Address(null, "325 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		Image i10 = new Image(null, "lien image10");//Utilisateur
		User u4 = new User(null, "Nekosuke", "PLAITON",Date.valueOf("1980-03-25"),"suke","jsuiscoach",true,a10,i10,0);
		
		Address a11 = new Address(null, "75 Avenue jean Lolive","93500","Pantin");
		Image i11 = new Image(null, "lien image11");//Utilisateur
		User u5 = new User(null, "Heirosuke", "PLATINI",Date.valueOf("1970-03-25"),"Bolo","jsuiscoachaussi",true,a11,i11,0);
		
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
		Sport s2 = new Sport(null,"Natation",i5);
		Sport s3 = new Sport(null,"Football",i8);
		Sport s4 = new Sport(null,"Basketball",i6);
		
		Event e1 = new Event(null,"Evenement de course à pied!","10 Km de Paris",Timestamp.valueOf("2022-12-25 10:00:00"),Timestamp.valueOf("2022-12-25 12:00:00"),0,a4,i5,s1);
		Event e2 = new Event(null,"Evenement d'escalade","Ascension du MontHugual, Débutants acceptés",Timestamp.valueOf("2022-12-10 08:00:00"),Timestamp.valueOf("2022-12-10 16:00:00"),0,a5,i6,s2);
		Event e3 = new Event(null,"Compétition Natation","200 et 400m Nage libre",Timestamp.valueOf("2022-12-15 18:30:00"),Timestamp.valueOf("2022-12-15 20:15:00"),20,a5,i6,s3);
		
		Friend f1 = new Friend(null,u1,u2,false);
	    Friend f2 = new Friend(null,u1,u3,false);
	    Friend f3 = new Friend(null,u2,u3,true);
	    
	    UserSport us1 = new UserSport(u1,s1,100);
	    UserSport us2 = new UserSport(u4,s3,200);
	    UserSport us3 = new UserSport(u5,s2,300);
	    UserSport us4 = new UserSport(u1,s3,100);
	    UserSport us5 = new UserSport(u4,s2,200);
	    UserSport us6 = new UserSport(u5,s1,300);
	    UserSport us7 = new UserSport(u1,s2,100);
	    UserSport us8 = new UserSport(u2,s1,200);
	    UserSport us9 = new UserSport(u3,s3,300);
	   

	    Produit p1 = new Produit(null,"Avatar tête de chat",9,i7);
	    Produit p2 = new Produit(null,"Avatar tête de singe",40,i8);
	    Produit p3 = new Produit(null,"Avatar casque de moto",15,i9);
		

	    Message m1 = new Message(null,Timestamp.valueOf("2022-12-25 10:00:00"),"Salut, comment vas-tu? Je me demandais si ça te dirait d'aller à la Salle à 19h ?",u1);
	    Messagerie msg1= new Messagerie(u2,m1); // Message jeanval to joline
	    
	    Message m2 = new Message(null,Timestamp.valueOf("2022-12-25 10:10:00"),"Ouaip Pourquoi pas! Mais tu payes l'entrée :p",u2);
	    Messagerie msg2= new Messagerie(u1,m2); // Message jolie to jeanval
	    
	    Message m3 = new Message(null,Timestamp.valueOf("2022-12-25 10:20:00"),"T'inquiète, je peux inviter des gens avec mon abonnement",u1);
	    Messagerie msg3= new Messagerie(u2,m3); // Message jeanval to joline
	    
	    Message m4 = new Message(null,Timestamp.valueOf("2022-12-25 10:30:00"),"Ah bah faisons comme ça!",u2);
	    Messagerie msg4= new Messagerie(u1,m4); // Message joline to jeanval
	    
	    Message m5 = new Message(null,Timestamp.valueOf("2022-12-25 10:40:00"),"Hello, tu peux me dire où se trouve le gymnase dont tu m'avais parlé pour leclub de Basket loisir ?",u5);
	    Messagerie msg5= new Messagerie(u2,m5); // Message josuke to joline
	    



		
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
	    userSportRepo.save(us4);
	    userSportRepo.save(us5);
	    userSportRepo.save(us6);
	    userSportRepo.save(us7);
	    userSportRepo.save(us8);
	    userSportRepo.save(us9);
	    
	    produitRepo.save(p1);
	    produitRepo.save(p2);
	    produitRepo.save(p3);
	    
	    messageRepo.save(m1);
	    messagerieRepo.save(msg1);
	    
	    messageRepo.save(m2);
	    messagerieRepo.save(msg2);
	    
	    messageRepo.save(m3);
	    messagerieRepo.save(msg3);
	    
	    messageRepo.save(m4);
	    messagerieRepo.save(msg4);
	    
	    messageRepo.save(m5);
	    messagerieRepo.save(msg5);
		
		
	}

}

