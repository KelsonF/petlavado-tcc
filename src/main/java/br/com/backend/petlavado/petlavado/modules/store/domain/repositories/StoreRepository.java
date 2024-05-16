package br.com.backend.petlavado.petlavado.modules.store.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
  Store findByEmail(String email);
}
