package br.com.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.model.Topico;
import br.com.forum.repository.TopicoRepository;


@RestController
@RequestMapping("/api/topicos")
@CrossOrigin
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	
	@GetMapping
	public List<Topico> buscarTodos() {	
		return topicoRepository.findAll();
	}
	
	@PostMapping
	public Topico salvar(@RequestBody Topico topico) {
		return topicoRepository.save(topico);
	}
}
