package br.com.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.model.Forum;
import br.com.forum.repository.ForumRepository;


@RestController
@RequestMapping("/api/forums")
@CrossOrigin
public class ForumController {
	
	@Autowired
	private ForumRepository forumRepository;
	
	@GetMapping
	public List<Forum> buscarTodos() {	
		return forumRepository.findAll();
	}
	
	@PostMapping
	public Forum salvar(@RequestBody Forum forum) {
		return forumRepository.save(forum);
	}
}
