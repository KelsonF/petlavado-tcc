package br.com.backend.petlavado.petlavado.modules.products.domain.repositories;

import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findProductByStore(Store store);
    List<Product> findProductByDescriptionContainingIgnoreCaseOrderByStore(String searchTerm);
}
