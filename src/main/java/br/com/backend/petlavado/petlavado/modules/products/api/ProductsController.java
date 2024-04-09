package br.com.backend.petlavado.petlavado.modules.products.api;

import br.com.backend.petlavado.petlavado.modules.products.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.products.domain.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {
    private final ProductService productService;

    ProductsController(
            ProductService productService
    ){
        this.productService = productService;
    }
    @GetMapping("list-all")
    public ResponseEntity<List<Product>> listAllProducts(){
        List<Product> productList = productService.listAllProducts();
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
}
