package br.com.backend.petlavado.petlavado.modules.store.api;

import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.store.domain.services.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity<Collection<Product>> listAllProducts(){
        Collection<Product> productList = productService.listAllProducts();
        return ResponseEntity.ok(productList);

    }

    @GetMapping("products/location")
    public ResponseEntity<Collection<Product>> listProductsByLocation(@RequestParam String location){
        Collection<Product> products = productService.listAllProductsByLocation(location);

        return ResponseEntity.ok(products);
    }

    @GetMapping("products/search")
    public ResponseEntity<Collection<Product>> listAllProductsByTerm(@RequestParam String searchTerm){
        Collection<Product> productList = productService.getProductBySearchTerm(searchTerm);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{storeId}/products")
    public ResponseEntity<Collection<Product>> listAllProductsByStore(@PathVariable Integer storeId){
        Collection<Product> productList = productService.listProductsByStore(storeId);
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
