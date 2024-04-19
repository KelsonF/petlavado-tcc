package br.com.backend.petlavado.petlavado.modules.store.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
