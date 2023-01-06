package br.com.adria.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adria.forum.model.UsuarioModel;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByLogin(String login);

}
