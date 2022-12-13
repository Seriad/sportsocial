package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.repository.MessageRepository;

@RestController @CrossOrigin("*")
public class MessageRest {
@Autowired
private MessageRepository messageRepo;

}
