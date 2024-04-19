package br.com.backend.petlavado.petlavado.modules.security.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
