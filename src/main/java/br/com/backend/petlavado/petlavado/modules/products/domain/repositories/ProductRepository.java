package br.com.backend.petlavado.petlavado.modules.products.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByDescriptionContainingIgnoreCaseOrderByStore(String searchTerm);
    List<Product> findProductByStore(Store store);
}
