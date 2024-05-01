package br.com.backend.petlavado.petlavado.modules.client.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.client.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String email);
}
