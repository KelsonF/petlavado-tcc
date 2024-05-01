package br.com.backend.petlavado.petlavado.modules.security.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findbyEmail(String email);
}
