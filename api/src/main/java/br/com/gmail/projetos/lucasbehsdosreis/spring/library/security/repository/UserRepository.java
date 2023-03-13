package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.repository;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
