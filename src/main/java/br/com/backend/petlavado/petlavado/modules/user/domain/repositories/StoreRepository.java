package br.com.backend.petlavado.petlavado.modules.user.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
