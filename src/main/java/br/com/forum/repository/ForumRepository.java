package br.com.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long>{

}
