package br.com.backend.petlavado.petlavado.modules.client.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.client.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByEmail(String email);
}
