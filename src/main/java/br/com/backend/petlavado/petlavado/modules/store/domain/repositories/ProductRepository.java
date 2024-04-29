package br.com.backend.petlavado.petlavado.modules.store.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findProductByDescriptionContainingIgnoreCaseOrderByStore(String searchTerm);
    List<Product> findProductByStore(Store store);
}
