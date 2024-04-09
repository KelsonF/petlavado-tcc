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

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> listProductsByStore(Integer storeId){
        Store store = storeService.getStoreOrNull(storeId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store with id" + storeId + "was not found")
        );

        return productRepository.findProductByStore(store);
    }

    public Product createProduct(Integer storeId, ProductDto productDto){
        Store store = storeService.getStoreOrNull(storeId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store with ID " + storeId + " not found")
        );

        Product newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);

        newProduct.setStore(store);

        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer storeId, Integer productId, ProductDto data){

        Store store = storeService.getStoreOrNull(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Store with ID " + storeId + " not found"));


        Product existingProduct = getProductOrNull(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with ID " + productId + " not found"));


        if (!existingProduct.getStore().getId().equals(storeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with ID " + productId + " does not belong to store with ID " + storeId);
        }

        BeanUtils.copyProperties(data, existingProduct);

        return productRepository.save(existingProduct);
    }

    public Optional<Product> getProductOrNull(Integer productId){
        return productRepository.findById(productId);
    }
}
