package br.com.backend.petlavado.petlavado.modules.products.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findProductByDescriptionContainingIgnoreCaseOrderByStore(
    String searchTerm
  );
  List<Product> findProductByStore(Store store);
}
