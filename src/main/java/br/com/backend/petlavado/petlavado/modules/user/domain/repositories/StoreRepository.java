package br.com.backend.petlavado.petlavado.modules.user.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
