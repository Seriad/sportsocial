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

import fr.solutec.entities.Friend;
import fr.solutec.entities.User;
import fr.solutec.repository.FriendRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class FriendRest {
	
	 @Autowired
	    private FriendRepository friendRepos;
	    @Autowired
	    private UserRepository userRepos;

	 

	    @PostMapping("friend/{idUser}") //demande
	    private Friend createFriendship (@RequestBody Friend f, @PathVariable Long idUser) {
	    	Optional<User> u = userRepos.findById(idUser);
	    	f.setApplicant(u.get());
	        return friendRepos.save(f);
	    }

	 

	    @PutMapping("friend/accept/{idFriend}") //accepter la demande
	    private Friend acceptFriendship (@RequestBody Friend f, @PathVariable Long idFriend ) {
	        f.setIdFriend(idFriend);
	        return friendRepos.save(f);    
	    }

	 

	    @DeleteMapping("friend/refuse/{idFriend}") //Refuser ou supprimer une amitié
	    private boolean refuseFriendship (@PathVariable Long idFriend) {
	        Optional<Friend> f = friendRepos.findById(idFriend);
	        if (f.isPresent()) {
	            friendRepos.deleteById(idFriend);
	            return true;
	        }else {
	            return false;
	        }
	    }


	    @GetMapping("friend/receiver/{idReceiver}") //Voir ces amis acceptés
	    private List<User> MyFriendship(@PathVariable Long idReceiver){
	        List<User> friends = new ArrayList <>();
	        List<Friend> recup = friendRepos.getMyFriends(idReceiver);
	        Optional<User> u = userRepos.findById(idReceiver);
	        if(u.isPresent()) {
	            for (Friend friend : recup) {
	                if(!friend.getApplicant().equals(u.get())) {
	                    friends.add(friend.getApplicant());
	                }
	                if(!friend.getReceiver().equals(u.get())) {
	                    friends.add(friend.getReceiver());
	                }
	            }
	        }
	        return friends;
	    }
	    
	    

}
