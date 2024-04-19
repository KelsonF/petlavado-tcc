package br.com.backend.petlavado.petlavado.modules.products.domain.services;

import br.com.backend.petlavado.petlavado.modules.products.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.products.domain.repositories.ProductRepository;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;

import br.com.backend.petlavado.petlavado.modules.store.domain.services.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<Product> listProductsByStore(UUID storeId){
        Store store = storeService.getStoreOrNull(storeId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store with id " + storeId + " was not found")
        );
        return productRepository.findProductByStore(store);
    }

    /**
     * Lists products by search term.
     */
    public List<Product> getProductBySearchTerm(String searchTerm){
        Assert.hasText(searchTerm, "Search term cannot be empty");

        return productRepository.findProductByDescriptionContainingIgnoreCaseOrderByStore(searchTerm);
    }

    /**
     * Creates a new product for the specified store.
     */
    public Product createProduct(UUID storeId, ProductDto data){
        Store store = storeService.getStoreOrNull(storeId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Store with ID " + storeId + " not found")
        );

        return productRepository.save(
                new Product(
                        data.getDescription(),
                        data.getValue(),
                        data.getImageUrl(),
                        store
                )
        );
    }

    /**
     * Updates an existing product for the specified store.
     */
    public Product updateProduct(UUID storeId, UUID productId, ProductDto data){
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
    public void deleteProduct(UUID storeId, UUID productId){

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
    public Optional<Product> getProductOrNull(UUID productId){
        return productRepository.findById(productId);
    }
}