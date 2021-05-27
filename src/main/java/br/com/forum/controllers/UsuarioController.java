package br.com.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.model.Usuario;
import br.com.forum.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> buscarTodos() {	
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario buscarPorId(@PathVariable("id") Long id) {
		return usuarioRepository.findById(id).get();
	}
	
	@PostMapping
	public Usuario salvar(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
