package br.com.backend.petlavado.petlavado.modules.store.domain.services;

import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.repositories.ProductRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;

    public ProductService(
        ProductRepository productRepository,
        StoreService storeService
    ){
        this.productRepository = productRepository;
        this.storeService = storeService;
    }

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> listProductsByStore(Integer storeId){
        Store store = storeService.getStoreById(storeId);
        return productRepository.findProductByStore(store);
    }

    public List<Product> getProductBySearchTerm(String searchTerm){
        Assert.hasText(searchTerm, "Search term cannot be empty");

        return productRepository.findProductByDescriptionContainingIgnoreCaseOrderByStore(searchTerm);
    }

    public Product createProduct(Integer storeId, ProductDto data){
        Store store = storeService.getStoreById(storeId);

        return productRepository.save(
                new Product(
                        data.getDescription(),
                        data.getValue(),
                        data.getImageUrl(),
                        store
                )
        );
    }

    public Product updateProduct(Integer storeId, Integer productId, ProductDto data){
        storeService.getStoreById(storeId);

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

    public void deleteProduct(Integer storeId, Integer productId){

        storeService.getStoreById(storeId);

        Product product = getProductOrNull(productId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id" + productId + "was not found")
        );

        productRepository.delete(product);
    }

    public Optional<Product> getProductOrNull(Integer productId){
        return productRepository.findById(productId);
    }
}