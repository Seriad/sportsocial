package fr.solutec;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.solutec.entities.Activity;
import fr.solutec.entities.Address;
import fr.solutec.entities.Club;
import fr.solutec.entities.Comment;
import fr.solutec.entities.Event;
import fr.solutec.entities.Friend;
import fr.solutec.entities.Image;
import fr.solutec.entities.Message;
import fr.solutec.entities.Messagerie;
import fr.solutec.entities.Post;
import fr.solutec.entities.Produit;
import fr.solutec.entities.Sport;
import fr.solutec.entities.Team;
import fr.solutec.entities.Training;
import fr.solutec.entities.User;
import fr.solutec.entities.UserActivity;
import fr.solutec.entities.UserSport;
import fr.solutec.repository.ActivityRepository;
import fr.solutec.repository.AddressRepository;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.CommentRepository;
import fr.solutec.repository.EventRepository;
import fr.solutec.repository.FriendRepository;
import fr.solutec.repository.ImageRepository;
import fr.solutec.repository.MessageRepository;
import fr.solutec.repository.MessagerieRepository;
import fr.solutec.repository.PostRepository;
import fr.solutec.repository.ProduitRepository;
import fr.solutec.repository.SportRepository;
import fr.solutec.repository.TeamRepository;
import fr.solutec.repository.TrainingRepository;
import fr.solutec.repository.UserActivityRepository;
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
	@Autowired
	private ActivityRepository activityRepo;
	@Autowired
	private UserActivityRepository userActivityRepo;
	@Autowired
	private ClubRepository clubRepo;
	@Autowired
	private TeamRepository teamRepo;
	@Autowired
	private PostRepository postRepo;	
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private TrainingRepository trainingRepo;


	public static void main(String[] args) {
		SpringApplication.run(SportSocialApplication.class, args);
		System.out.println("Lancement terminé");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		//EMPTY ADDRESS TO ADD TO A NEWLY CREATED USER, DO NOT DELETE. IT IS USED IN THE USER CREATION API
		Address a0 = new Address(null, "","","");
		addressRepo.save(a0);
		
		Address a1 = new Address(null, "2 rue Pomme","75002","Paris");
		//DONT DELETE Image i1, THIS IMAGE IS USED AS A DEFAULT PROFILE PIC IN USER CLASS
		Image i1 = new Image(null, "https://media.istockphoto.com/id/1018999828/fr/vectoriel/ic%C3%B4ne-de-profil-avatar-par-d%C3%A9faut-espace-r%C3%A9serv%C3%A9-de-photo-gris.jpg?s=170667a&w=0&k=20&c=yZAkya7Wg7vtsu8FF86k5r_1IrkE6jp4hwl7sf6bXJ0="); //Utilisateur
		imageRepo.save(i1);
		User u1 = new User(null, "Valjean", "Jean",Date.valueOf("1990-03-25"),"JeanVal","123",false,a1,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a2 = new Address(null, "29 Bd Camélinat","92240","Malakoff");
		User u2 = new User(null,  "Cujoh","Joline",Date.valueOf("2000-07-25"),"JoFitness","cujoh",true,a2,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a3 = new Address(null, "322 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		User u3 = new User(null,  "Dufour","Stéphanie",Date.valueOf("1970-10-05"),"Steph","456",false,a3,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a10 = new Address(null, "325 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		User u4 = new User(null,  "Petit","Manuel",Date.valueOf("1980-10-10"),"Manu","111",true,a10,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a11 = new Address(null, "75 Avenue jean Lolive","93500","Pantin");
		User u5 = new User(null,  "Perrera","Antoine",Date.valueOf("1970-08-23"),"Anto","jsuiscoachaussi",true,a11,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a12 = new Address(null, "2 rue Solutec","75008","Paris");
		User u12 = new User(null, "Cantona", "Eric",Date.valueOf("1977-03-25"),"Footman","123",false,a1,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a13 = new Address(null, "29 Bd Lecompte","31000","Toulouse");
		User u13 = new User(null,  "Dupont","Antoine",Date.valueOf("2000-07-25"),"ToulouseRBG","bestplayer",true,a2,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a14 = new Address(null, "322 Boulevard des patines","13000","Marseille");
		User u14 = new User(null,  "Dufond","Stéphane",Date.valueOf("1979-10-25"),"Stephou","456",false,a3,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a15 = new Address(null, "325 Boulevard de l'écume","92200","Neuilly-Sur-Seine");
		User u15 = new User(null,  "Grand","Manuel",Date.valueOf("1966-07-07"),"BigManu","111",true,a10,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a16 = new Address(null, "39 Avenue jean Lorange","33000","Bordeaux");
		User u16 = new User(null,  "Poirier","Jacques",Date.valueOf("1970-04-23"),"Jacqui","poire",true,a11,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a17 = new Address(null, "2 rue du livre","75006","Paris");
		User u17 = new User(null, "Hugo", "Victor",Date.valueOf("1953-03-21"),"Miserable","123",false,a1,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a18 = new Address(null, "29 avenue Esic","92240","Malakoff");
		User u18 = new User(null,  "Formateur","Joel",Date.valueOf("1993-07-25"),"Jojo","esic",true,a2,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a19 = new Address(null, "322 Boulevard de l'envie","75012","Paris");
		User u19 = new User(null,  "Zidane","Zinedine",Date.valueOf("1998-06-20"),"Champ","123",false,a3,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a20 = new Address(null, "3 rue du bureau","30000","Nime");
		User u20 = new User(null,  "Adi","Jacques",Date.valueOf("1985-11-11"),"Jaja","987",true,a10,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a21 = new Address(null, "75 Avenue la truite","29200","Brest");
		User u21 = new User(null,  "Kern","Loic",Date.valueOf("1971-08-01"),"Akenavo","phare",true,a11,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a22 = new Address(null, "2 rue du Nord","59000","Lille");
		User u22 = new User(null, "Monceau", "Jules",Date.valueOf("1984-06-15"),"Juju","123",false,a1,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		Address a23 = new Address(null, "29 Bd Symphonie","69000","Lyon");
		User u23 = new User(null,  "Mozart","Wolfgang",Date.valueOf("2000-04-21"),"Musique","son",true,a2,i1,0,new ArrayList<Long>(Arrays.asList(i1.getIdImage())));
		
		
		Address a4 = new Address(null, "73 Rue des sportifs","75008","Paris");
		Address a5 = new Address(null, "25 Rue de Lacretelle","75168","Saint-Maur-des-Fossés");
		Address a6 = new Address(null, "3 Avenue du Général Leclerc","92200","Neuilly-Sur-Seine");
		
		Image i4 = new Image(null, "https://img.freepik.com/vecteurs-libre/affiche-fitness-homme-cours-execution_1284-12590.jpg?w=1060&t=st=1671395275~exp=1671395875~hmac=6b7d03a193f0d9544c124a55d7ad60659765a9375b350299c740b7e553c63bcd");//Sport
		Image i6 = new Image(null, "https://img.freepik.com/vecteurs-libre/ensemble-elements-basket-vintage_1284-39314.jpg?w=826&t=st=1671391968~exp=1671392568~hmac=1dd55bf9ec92ea81b48263b3fb498b48cd45535e4e069c13b61188bb393b459f");//Sport
		
		Image i8 = new Image(null, "https://img.freepik.com/vecteurs-premium/joueur-football-abstrait-tirant-rapidement-balle-eclaboussure-aquarelles-illustration-peintures_291138-342.jpg?w=826");//Event
		Image i9 = new Image(null, "https://img.freepik.com/vecteurs-libre/ensemble-elements-sport-rugby-vintage_1284-37924.jpg?w=826&t=st=1670941713~exp=1670942313~hmac=ffce7d716ce1183f9286475a795bc2cd1e53c7f08439ca67134829b90669417d");//Event
		
		Image i12= new Image(null, "https://img.freepik.com/vecteurs-libre/portrait-chat-cravate-lunettes-hipster-regard-isole-illustration-vectorielle_1284-1931.jpg?w=740&t=st=1671217853~exp=1671218453~hmac=78a64242cef183b74f0652c04ec43ad54b568a43e6c8d62f5ec333df365f6d8e");
		Image i13= new Image(null, "https://img.freepik.com/vecteurs-libre/tete-gorille-style-monochrome_225004-461.jpg?w=826&t=st=1671394323~exp=1671394923~hmac=b310f5f4a53bf75f456b9ca38a3d3c9cf9accb8c66ba529a726d7367920f0129");
		Image i14= new Image(null, "https://cdn-icons-png.flaticon.com/512/1061/1061216.png?w=826&t=st=1671394373~exp=1671394973~hmac=c2a1cbed743d21a3a7ffda4282a79f4ee2fd2ff14b634007f1051ced2ff26e86");
		
		Image i15= new Image(null, "https://img.freepik.com/photos-gratuite/grimpeur-mur-escarpe_23-2147665027.jpg?w=1380&t=st=1671394819~exp=1671395419~hmac=7ce9cc84e2f2fb6e3995592cc48d30491197ed39ef119541c1072cc80f2be4d2");
		Image i16= new Image(null, "https://img.freepik.com/photos-gratuite/nageur-male-nageant-coup-papillon_171337-7613.jpg?w=1380&t=st=1671395097~exp=1671395697~hmac=ad8f6662e82ad73d918ad02a60510f504f5f04b48590974a5d0aada901b4c4c9");
		
		Image i17= new Image(null, "https://img.freepik.com/photos-gratuite/joueur-football-portant-maillot-numero-10_53876-146599.jpg?w=1380&t=st=1672415588~exp=1672416188~hmac=60dd7c4a646d706b5530c388a0e7b4987e8fc1b6bf84550c2f7d3c7f1e655a2f");
		imageRepo.save(i17);
		
		Image i18= new Image(null, "https://img.freepik.com/photos-gratuite/joueurs-football-action-stade-professionnel_654080-1194.jpg?w=1060&t=st=1672908598~exp=1672909198~hmac=4c50d90e0c6c6b15daea737cba46ded0e5112d3697e19e0cd60011a1731222ec");
		imageRepo.save(i18);
		
		Image i19= new Image(null, "https://img.freepik.com/vecteurs-libre/employes-donnant-mains-aidant-leurs-collegues-monter-escaliers_74855-5236.jpg?w=1060&t=st=1672913654~exp=1672914254~hmac=5575146b381963534bad888d4b17ba15718e7fd0cfa859afcc31555559ecfaa0");
		imageRepo.save(i19);
		
		
		Sport s1 = new Sport(null,"Course à pied",i4);
		Sport s2 = new Sport(null,"Natation",i16);
		Sport s3 = new Sport(null,"Football",i8);
		Sport s4 = new Sport(null,"Basketball",i6);
		Sport s5 = new Sport(null, "Rugby", i9);
		Sport s6 = new Sport(null, "Escalade", i15);
		
	    Message m1 = new Message(null,Timestamp.valueOf("2022-12-25 10:00:00"), true,"Salut, comment vas-tu? Je me demandais si ça te dirait d'aller à la Salle à 19h ?",u1);
	    Messagerie msg1= new Messagerie(u2,m1); // Message jeanval to joline
	    
	    Message m2 = new Message(null,Timestamp.valueOf("2022-12-26 10:10:00"), true,"Oui ! Pourquoi pas! Mais tu payes l'entrée :p",u2);
	    Messagerie msg2= new Messagerie(u1,m2); // Message jolie to jeanval
	    
	    Message m3 = new Message(null,Timestamp.valueOf("2022-12-27 10:20:00"), true,"T'inquiète, je peux inviter des gens avec mon abonnement",u1);
	    Messagerie msg3= new Messagerie(u2,m3); // Message jeanval to joline
	    
	    Message m4 = new Message(null,Timestamp.valueOf("2022-12-28 10:30:00"), true,"Ah bah faisons comme ça!",u2);
	    Messagerie msg4= new Messagerie(u1,m4); // Message joline to jeanval
	    
	    Message m5 = new Message(null,Timestamp.valueOf("2022-12-29 10:40:00"), true,"Hello, tu peux me dire où se trouve le gymnase dont tu m'avais parlé pour le club de Basket loisir ?",u5);
	    Messagerie msg5= new Messagerie(u2,m5); // Message josuke to joline
		
		List<User> membres1 = new ArrayList<>();
		List<User> membres2 = new ArrayList<>();
		
		membres1.add(u1);
		membres1.add(u2);
		membres1.add(u13);
		
		membres2.add(u1);

		Message m6 = new Message(null,Timestamp.valueOf("2022-12-25 11:00:00"), true,"Salut, Rugby ce soir ?",u1);
  
	    Message m7 = new Message(null,Timestamp.valueOf("2022-12-25 11:10:00"), true,"Oui ! rendez-vous à 19h au parc",u2);

	    Message m8 = new Message(null,Timestamp.valueOf("2022-12-25 11:30:00"), true,"Trop cool, à ce soir !",u13);

		
		List<Message> conversation1 = new ArrayList<>();
		List<Message> conversation2 = new ArrayList<>();
		
		conversation1.add(m6);
		conversation1.add(m7);
		conversation1.add(m8);
		 
		Team t1 = new Team(null,"Les rugbymen",u2 ,i17 ,membres1, conversation1);
		
		Team t2 = new Team(null,"Les footeux",u1 ,i18 ,membres2, conversation2);
		
		List<User> participantse1 = new ArrayList<>();
		List<User> participantse2 = new ArrayList<>();
		List<User> participantse3 = new ArrayList<>();
		
		participantse1.add(u1);
		participantse1.add(u2);
		participantse1.add(u3);
		participantse2.add(u4);
		participantse1.add(u5);
		participantse3.add(u3);
		participantse3.add(u2);
		participantse3.add(u5);
		
		Event e1 = new Event(null,"Evènement de course à pied!","10 Km de Paris",Timestamp.valueOf("2022-12-25 10:00:00"),Timestamp.valueOf("2022-12-25 12:00:00"),0,participantse1,a4,s1, false);
		Event e2 = new Event(null,"Evènement d'escalade","Ascension du MontHugual, Débutants acceptés",Timestamp.valueOf("2023-02-10 08:00:00"),Timestamp.valueOf("2023-02-10 16:00:00"),0,participantse2,a5,s6, false);
		Event e3 = new Event(null,"Compétition Natation","200 et 400m Nage libre",Timestamp.valueOf("2023-05-15 18:30:00"),Timestamp.valueOf("2023-05-15 20:15:00"),20,participantse3,a5,s2, false);
		
		Friend f1 = new Friend(null,u1,u2,true);
	    Friend f2 = new Friend(null,u1,u3,true);
	    Friend f3 = new Friend(null,u2,u3,true);
	    Friend f4 = new Friend(null,u2,u5,true);
	    Friend f5 = new Friend(null,u2,u4,false);
	    
	    UserSport us1 = new UserSport(u1,s1,100);
	    UserSport us2 = new UserSport(u4,s3,200);
	    UserSport us3 = new UserSport(u5,s2,300);
	    UserSport us4 = new UserSport(u1,s3,100);
	    UserSport us5 = new UserSport(u4,s2,200);
	    UserSport us6 = new UserSport(u5,s1,300);
	    UserSport us7 = new UserSport(u1,s2,100);
	    UserSport us8 = new UserSport(u2,s1,200);
	    UserSport us9 = new UserSport(u3,s3,300);
	    UserSport us10 = new UserSport(u13,s6,400);
	    UserSport us11 = new UserSport(u15,s4,300);
	    UserSport us12 = new UserSport(u16,s6,200);
	    UserSport us13 = new UserSport(u18,s1,600);
	    
	   

	    Produit p1 = new Produit(null,"Avatar tête de chat",9,i12);
	    Produit p2 = new Produit(null,"Avatar tête de singe",40,i13);
	    Produit p3 = new Produit(null,"Avatar casque de moto",15,i14);
	    
	    List<User> membresClub1 = new ArrayList<>();
	    List<User> membresClub2 = new ArrayList<>();
	    List<User> membresClub3 = new ArrayList<>();
	    
	    membresClub1.add(u2);
	    membresClub2.add(u1);
	    membresClub3.add(u4);
	    membresClub1.add(u1);
	    membresClub1.add(u15);
	    membresClub1.add(u20);
	    membresClub1.add(u23);
	    
	    Club c1 = new Club(null,"Paris Rugby", "Club de rugby amateur. Vous souhaitez découvrir le ballon oval ? Envoyez nous un message et venez tenter votre chance", i9, s5,u2,membresClub1);
	    Club c2 = new Club(null,"Les amoureux du foot", "Les fans du ballon rond vous invitent à les rejoindre dans ce groupe dédié aux amateurs comme aux professionnels qui seraient intéréssé dans le partage de leur passion !", i8, s3,u1,membresClub2);
	    Club c3 = new Club(null,"Basket fans", "Vous êtes basketteurs ou simplement fans de ce sport ? Venez nous rejoindre pour discuter des évènements à venir et des matchs (récents comme plus anciens).", i6, s4, u4, membresClub3);
	    
	    Activity ac1 = new Activity(null,10.1,"Footing",Timestamp.valueOf("2022-12-25 10:10:5"),Timestamp.valueOf("2022-12-25 10:30:00"),"Course de remise en forme",true, a4,s1);
	    Activity ac2 = new Activity(null,2.,"Natation",Timestamp.valueOf("2022-12-25 14:23:5"),Timestamp.valueOf("2022-12-25 15:30:00"),"Natation, 2km",true,a5,s2);
	    Activity ac3 = new Activity(null,0.,"Football",Timestamp.valueOf("2022-12-25 14:10:0"),Timestamp.valueOf("2022-12-25 18:30:00"),"Tournois de football",true,a6,s3);
	    Activity ac4 = new Activity(null,10,"Tracking mont blanc",Timestamp.valueOf("2023-12-25 10:10:5"),Timestamp.valueOf("2023-12-25 10:30:00"),"On va gravir des sommets",true,a4,s1);
	    Activity ac5 = new Activity(null,0.,"Rugby avec les copains",Timestamp.valueOf("2023-05-25 14:23:5"),Timestamp.valueOf("2023-05-25 15:30:00"),"Entrainement à la mélée",false,a5,s5);
	    Activity ac6 = new Activity(null,0.,"Concour de tir à 3 points",Timestamp.valueOf("2023-07-10 14:10:0"),Timestamp.valueOf("2023-07-10 18:30:00"),"Séance au square Montparnasse",true,a6,s4);
	    
	    UserActivity uac1= new UserActivity(u2,ac1);
	    UserActivity uac2= new UserActivity(u1,ac2);
	    UserActivity uac3= new UserActivity(u1,ac3);
	    UserActivity uac4= new UserActivity(u2,ac4);
	    UserActivity uac5= new UserActivity(u3,ac5);
	    UserActivity uac6= new UserActivity(u5,ac6);
	    
	    Comment com1 = new Comment(null, null, "Je suis d'accord avec toi, Joline. C'était vraiment un match incroyable à regarder. Les joueurs ont donné tout ce qu'ils avaient et cela s'est vu dans leur jeu. La défense était impénétrable et l'attaque était superbe. Félicitations à l'équipe pour cette victoire méritée!", u15,null, null,0,0);
		Comment com2 = new Comment(null, null, "Je suis déçu de l'issue de ce match. Bien que l'équipe ait gagné, je pense qu'ils ont manqué d'agressivité et d'énergie. La défense était trop passive et l'attaque n'a pas réussi à marquer autant de points qu'elle aurait dû. Il y a encore beaucoup de choses à améliorer pour l'équipe si elle veut gagner les prochains matchs.", u20,null, null, 0,0);
	    
		List<Comment> comList3 = new ArrayList<Comment>();
		List<Comment> comList4 = new ArrayList<Comment>();
		
		
		Comment com3 = new Comment(null, null, "Je suis tellement excité pour le prochain match de rugby amateur ! J'ai hâte de voir Les lions rugissants en action sur le terrain. Je sais qu'ils ont travaillé dur pour se préparer pour ce match et j'ai hâte de voir leur performance. Je vais amener toute ma famille pour soutenir l'équipe locale et passer un bon moment ensemble. Je suis prêt à crier et à soutenir les lions rugissants jusqu'à la fin ! ", u20,null, comList3,0,0);
		Comment com4 = new Comment(null, null, "Je suis tellement excité de venir au prochain match de rugby amateur ! C'est toujours amusant de se rassembler avec des amis et de soutenir l'équipe locale. Je suis impatient de voir Les lions rugissants en action sur le terrain, ils ont l'air d'être une équipe solide et talentueuse. Je vais certainement acheter un t-shirt de l'équipe pour montrer mon soutien. J'ai hâte de passer un bon moment et de profiter de l'ambiance de match de rugby !", u1, null, null,0,0);
		Comment com5 = new Comment(null, null, "Cher supporter, Nous sommes ravis de lire votre enthousiasme pour le prochain match de rugby amateur ! Nous sommes heureux que vous ayez l'intention de venir avec votre famille et vos amis pour soutenir l'équipe locale. Les  lions rugissants sont une équipe solide et talentueuse et ils apprécieront certainement votre soutien sur le terrain.Nous vous rappelons que des t-shirts de l'équipe seront disponibles à l'achat le jour du match, ainsi que d'autres articles de supporter. Nous espérons que vous passerez un bon moment et que vous profiterez de l'ambiance de match de rugby. Merci de votre soutien continu et nous espérons vous voir à notre prochaine rencontre. Cordialement,Le Comité du club", u15, null,null,0,0);
		Comment com6 = new Comment(null, null, "Je suis tellement excité pour le prochain match de rugby amateur ! Je sais que nous avons travaillé dur pour nous préparer pour cette rencontre, et je suis confiant que nous allons donner le meilleur de nous-mêmes sur le terrain. Je remercie les supporters pour leur soutien continu, cela nous donne un vrai coup de boost pour jouer. Je promets de donner tout ce que j'ai et de jouer avec cœur pour remporter la victoire pour l'équipe Les lions rugissants. \n #LionsRugissants #Teamwork", u23,null, comList4,0,0);
		Comment com7 = new Comment(null, null, "Je suis tellement impatient de voir ce joueur en action lors du prochain match de rugby amateur ! J'ai entendu parler de ses compétences et de sa détermination sur le terrain et je suis certain qu'il va être un atout pour l'équipe. J'ai hâte de voir comment il va contribuer à la victoire de notre équipe. Je vais amener ma famille et mes amis pour le soutenir et pour passer un bon moment ensemble. Je suis prêt à crier fort et à soutenir l'équipe jusqu'à la fin ! \n #teamspirit #victory", u1, null, null,0,0);
		Comment com8 = new Comment(null, null, "Je suis totalement d'accord avec toi ! Je suis également très impatient de voir ce joueur en action lors du prochain match de rugby amateur. J'ai entendu beaucoup de bien de lui et je suis convaincu qu'il sera un atout pour l'équipe.", u1, null, null,0,0);
		
		List<Comment> comList1 = new ArrayList<Comment>();
		List<Comment> comList2 = new ArrayList<Comment>();
		
		Training train1 = new Training(null, "S'échauffer pendant 10 mins puis courrir 30 mins en fractionné (5mins rapide / 5 minutes lent)", u2, s1);
		
		
		comList1.add(com1);
		comList1.add(com2);
		
		comList2.add(com3);
		comList2.add(com4);
		comList3.add(com5);
		comList2.add(com6);
		comList4.add(com7);
		comList4.add(com8);
		
		
		List<User> aimePost = new ArrayList<User>();
		
		aimePost.add(u15);
		aimePost.add(u20);
		
		
		List<User> aimePost2 = new ArrayList<User>();
		
		aimePost2.add(u20);
		aimePost2.add(u1);
		aimePost2.add(u23);
		
		
		String newLine = System.getProperty("line.separator");
		
	    Post post1 = new Post(null, null, "Quel match incroyable! Les joueurs ont joué avec passion et détermination, et cela a payé avec une victoire éclatante. La défense était solide et l'attaque a été impressionnante. Félicitations à toute l'équipe pour cette performance incroyable!", i6, u2, c1, aimePost, comList1,0, 0);
		Post post2 = new Post(null, null, "Chers amateurs de rugby, "
				+ newLine + "Nous avons le plaisir de vous annoncer que le prochain match de rugby amateur se tiendra au Stade de la ville le samedi suivant à 15 heures. L'équipe locale, les Les lions rugissants se mesureront à l'équipe Les guerriers de la plaine pour un match passionnant qui promet d'être rempli d'actions et d'émotions. "
				+ newLine + "Nous vous invitons à venir nombreux pour soutenir votre équipe locale et passer un moment agréable en famille ou entre amis. Des boissons et des collations seront disponibles à l'achat. "
				+ newLine + "Nous espérons vous voir là-bas pour cette journée de rugby! "
				+ newLine + "Cordialement,"
				+ newLine + "Le Comité organisateur", null, u15, c1, aimePost2, comList2,0,0);
		Post post3 = new Post(null, null,"Chers supporters du club,"
				+ newLine + "Nous avons le plaisir de vous annoncer l'arrivée de trois nouveaux joueurs de football dans notre équipe. Ces joueurs talentueux apporteront de nouvelles compétences et de l'énergie à notre équipe et nous sommes convaincus qu'ils contribueront à notre succès sur le terrain. "
				+ newLine + "Les nouveaux joueurs sont : "
				+ newLine + "Ahmed Benkirane, attaquant rapide et agile "
				+ newLine + "Samuel Martin, défenseur solide et expérimenté "
				+ newLine + "Baptiste Dupont, milieu de terrain créatif et technique "
				+ newLine + "Nous espérons que vous serez tous présents pour les accueillir lors de notre prochain match à domicile. Nous sommes certains qu'ils feront un excellent travail en portant les couleurs de notre club. "
				+ newLine + "Nous vous remercions de votre soutien continu et nous espérons vous voir à notre prochaine rencontre. "
				+ newLine + "Cordialement, "
				+ newLine + "Le Comité du club", i8, u23, c2, null, null,0,0);
		
		
		
		addressRepo.save(a1);
		userRepo.save(u1);
		
		addressRepo.save(a2);
		userRepo.save(u2);
		
		addressRepo.save(a3);
		userRepo.save(u3);
		
		addressRepo.save(a10);
		userRepo.save(u4);
		
		addressRepo.save(a11);
		userRepo.save(u5);
		
		addressRepo.save(a4);
		addressRepo.save(a5);
		addressRepo.save(a6);
		
		addressRepo.save(a12);
		userRepo.save(u12);
		
		addressRepo.save(a13);
		userRepo.save(u13);
		
		addressRepo.save(a14);
		userRepo.save(u14);
		
		addressRepo.save(a15);
		userRepo.save(u15);
		
		addressRepo.save(a16);
		userRepo.save(u16);
		
		addressRepo.save(a17);
		userRepo.save(u17);
		
		addressRepo.save(a18);
		userRepo.save(u18);
		
		addressRepo.save(a19);
		userRepo.save(u19);
		
		addressRepo.save(a20);
		userRepo.save(u20);
		
		addressRepo.save(a21);
		userRepo.save(u21);
		
		addressRepo.save(a22);
		userRepo.save(u22);
		
		addressRepo.save(a23);
		userRepo.save(u23);
		
		imageRepo.save(i4);
		imageRepo.save(i6);
		imageRepo.save(i8);
		imageRepo.save(i9);
		imageRepo.save(i12);
		imageRepo.save(i13);
		imageRepo.save(i14);
		imageRepo.save(i15);
		imageRepo.save(i16);
		
		sportRepo.save(s1);
		sportRepo.save(s2);
		sportRepo.save(s3);
		sportRepo.save(s4);
		sportRepo.save(s5);
		sportRepo.save(s6);
			
		eventRepo.save(e1);
		eventRepo.save(e2);
		eventRepo.save(e3);
		
		friendRepo.save(f1);
	    friendRepo.save(f2);
	    friendRepo.save(f3);
	    friendRepo.save(f4);
	    friendRepo.save(f5);
	    
	    userSportRepo.save(us1);
	    userSportRepo.save(us2);
	    userSportRepo.save(us3);
	    userSportRepo.save(us4);
	    userSportRepo.save(us5);
	    userSportRepo.save(us6);
	    userSportRepo.save(us7);
	    userSportRepo.save(us8);
	    userSportRepo.save(us9);
	    userSportRepo.save(us10);
	    userSportRepo.save(us11);
	    userSportRepo.save(us12);
	    userSportRepo.save(us13);
	    
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
	    
	    messageRepo.save(m6);
	    
	    messageRepo.save(m7);
	    
	    messageRepo.save(m8);


		activityRepo.save(ac1);
		activityRepo.save(ac2);
		activityRepo.save(ac3);
		activityRepo.save(ac4);
		activityRepo.save(ac5);
		activityRepo.save(ac6);
		
		userActivityRepo.save(uac1);
		userActivityRepo.save(uac2);
		userActivityRepo.save(uac3);
		userActivityRepo.save(uac4);
		userActivityRepo.save(uac5);
		userActivityRepo.save(uac6);
		
		clubRepo.save(c1);
		clubRepo.save(c2); 
		clubRepo.save(c3); 
	
		teamRepo.save(t1);
		teamRepo.save(t2);
		
		commentRepo.save(com1);
		commentRepo.save(com2);
		commentRepo.save(com5);
		commentRepo.save(com3);
		commentRepo.save(com4);
		commentRepo.save(com7);
		commentRepo.save(com8);
		commentRepo.save(com6);
		
		
		postRepo.save(post1);
		postRepo.save(post2);
		postRepo.save(post3);
		
		trainingRepo.save(train1);
		
		

		
		
	
	}

}

