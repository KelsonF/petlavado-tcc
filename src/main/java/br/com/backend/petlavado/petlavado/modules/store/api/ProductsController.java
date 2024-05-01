package br.com.backend.petlavado.petlavado.modules.store.api;

import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.store.domain.services.ProductService;

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
            ProductService productService
    ){
        this.productService = productService;
    }
    @GetMapping("products")
    public ResponseEntity<List<Product>> listAllProducts(){
        List<Product> productList = productService.listAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> listAllProductsByTerm(@RequestParam String searchTerm){
        List<Product> productList = productService.getProductBySearchTerm(searchTerm);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{storeId}/products")
    public ResponseEntity<List<Product>> listAllProductsByStore(@PathVariable Integer storeId){
        List<Product> productList = productService.listProductsByStore(storeId);
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/{storeId}/create")
    public ResponseEntity<Product> createProduct(@PathVariable Integer storeId, ProductDto productDto){
        Product createdProduct = productService.createProduct(storeId,productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/{storeId}/{productId}/update")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer productId,
            @PathVariable Integer storeId,
            ProductDto data
    ){
        Product updatedProduct = productService.updateProduct(storeId, productId, data);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{storeId}/{productId}/delete")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer storeId,
            @PathVariable Integer productId
    ){
        productService.deleteProduct(storeId, productId);
        return ResponseEntity.noContent().build();
    }
}
