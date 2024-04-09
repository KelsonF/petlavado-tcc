package br.com.backend.petlavado.petlavado.modules.products.domain.services;

import br.com.backend.petlavado.petlavado.modules.products.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.products.domain.repositories.ProductRepository;
import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.user.domain.services.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            StoreService storeService
    ){
        this.productRepository = productRepository;
        this.storeService = storeService;
    }

    /**
     * Lists all products.
     */
    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Lists products by store.
     */
    public List<Product> listProductsByStore(Integer storeId){
        Store store = storeService.getStoreOrNull(storeId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store with id " + storeId + " was not found")
        );
        return productRepository.findProductByStore(store);
    }

    /**
     * Creates a new product for the specified store.
     */
    public Product createProduct(Integer storeId, ProductDto productDto){
        Store store = storeService.getStoreOrNull(storeId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Store with ID " + storeId + " not found")
        );

        Product newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);

        newProduct.setStore(store);

        return productRepository.save(newProduct);
    }

    /**
     * Updates an existing product for the specified store.
     */
    public Product updateProduct(Integer storeId, Integer productId, ProductDto data){
        storeService.getStoreOrNull(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Store with ID " + storeId + " not found"));

        Product existingProduct = getProductOrNull(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with ID " + productId + " not found"));

        // Ensure the product belongs to the specified store
        if (!existingProduct.getStore().getId().equals(storeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with ID " + productId + " does not belong to store with ID " + storeId);
        }

        BeanUtils.copyProperties(data, existingProduct);

        return productRepository.save(existingProduct);
    }

    /**
     * Deletes a product.
     */
    public void deleteProduct(Integer storeId, Integer productId){

        storeService.getStoreOrNull(storeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Park with Id" + storeId + "not found")
        );

        Product product = getProductOrNull(productId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id" + productId + "was not found")
        );

        productRepository.delete(product);
    }

    /**
     * Retrieves a product by ID, returning null if not found.
     */
    public Optional<Product> getProductOrNull(Integer productId){
        return productRepository.findById(productId);
    }
}

