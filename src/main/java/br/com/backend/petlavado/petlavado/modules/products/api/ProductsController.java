package br.com.backend.petlavado.petlavado.modules.products.api;

import br.com.backend.petlavado.petlavado.modules.products.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.products.domain.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ProductsController {
    private final ProductService productService;

    ProductsController(
            ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> productList = productService.listAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> listAllProductsByTerm(@RequestParam String searchTerm) {
        List<Product> productList = productService.getProductBySearchTerm(searchTerm);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{storeId}/products")
    public ResponseEntity<List<Product>> listAllProductsByStore(@PathVariable UUID storeId) {
        List<Product> productList = productService.listProductsByStore(storeId);
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/{storeId}/create")
    public ResponseEntity<Product> createProduct(@PathVariable UUID storeId, ProductDto productDto) {
        Product createdProduct = productService.createProduct(storeId, productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/{storeId}/{productId}/update")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID productId,
            @PathVariable UUID storeId,
            ProductDto data) {
        Product updatedProduct = productService.updateProduct(storeId, productId, data);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{storeId}/{productId}/delete")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable UUID storeId,
            @PathVariable UUID productId) {
        productService.deleteProduct(storeId, productId);
        return ResponseEntity.noContent().build();
    }
}
