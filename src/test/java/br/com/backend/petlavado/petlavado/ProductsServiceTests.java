package br.com.backend.petlavado.petlavado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.backend.petlavado.petlavado.modules.products.domain.dtos.ProductDto;
import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.products.domain.repositories.ProductRepository;
import br.com.backend.petlavado.petlavado.modules.products.domain.services.ProductService;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.services.StoreService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock
  private ProductRepository productRepository;

  @Mock
  private StoreService storeService;

  @InjectMocks
  private ProductService productService;

  @Test
  void testListAllProducts() {
    // Mocking productRepository.findAll() to return a list of products
    when(productRepository.findAll())
      .thenReturn(
        Arrays.asList(
          new Product(
            "Product1",
            new BigDecimal("10.0"),
            "image1.jpg",
            new Store()
          ),
          new Product(
            "Product2",
            new BigDecimal("20.0"),
            "image2.jpg",
            new Store()
          )
        )
      );

    // Calling the service method to list all products
    List<Product> products = productService.listAllProducts();

    // Asserting that the returned list is not null and contains two products
    assertNotNull(products);
    assertEquals(2, products.size());
  }

  @Test
  void testListProductsByStore() {
    // Mocking storeService.getStoreById() to return a store
    Store store = new Store();
    when(storeService.getStoreById(1)).thenReturn(store);

    // Mocking productRepository.findProductByStore() to return a list of products
    when(productRepository.findProductByStore(store))
      .thenReturn(
        Arrays.asList(
          new Product("Product1", new BigDecimal("10.0"), "image1.jpg", store),
          new Product("Product2", new BigDecimal("20.0"), "image2.jpg", store)
        )
      );

    // Calling the service method to list products by store ID
    List<Product> products = productService.listProductsByStore(1);

    // Asserting that the returned list is not null and contains two products
    assertNotNull(products);
    assertEquals(2, products.size());
  }

  @Test
  void testCreateProduct() {
    // Mocking storeService.getStoreById() to return a store
    Store store = new Store();
    when(storeService.getStoreById(1)).thenReturn(store);

    // Mocking productRepository.save() to return a product
    ProductDto productDto = new ProductDto(
      "Product1",
      new BigDecimal("10.0"),
      "image1.jpg"
    );
    when(productRepository.save(any(Product.class)))
      .thenReturn(
        new Product("Product1", new BigDecimal("10.0"), "image1.jpg", store)
      );

    // Calling the service method to create a product
    Product createdProduct = productService.createProduct(1, productDto);

    // Asserting that the created product is not null
    assertNotNull(createdProduct);
    assertEquals("Product1", createdProduct.getDescription());
  }

  @Test
  void testGetProductOrNull() {
    // Mocking productRepository.findById() to return a product
    Product product = new Product(
      "Product1",
      new BigDecimal("10.0"),
      "image1.jpg",
      new Store()
    );
    when(productRepository.findById(1)).thenReturn(Optional.of(product));

    // Calling the service method to get a product by ID
    Optional<Product> retrievedProduct = productService.getProductOrNull(1);

    // Asserting that the retrieved product is present and matches the expected product
    assertTrue(retrievedProduct.isPresent());
    assertEquals(product, retrievedProduct.get());
  }

  @Test
  void testDeleteProduct() {
    // Mocking storeService.getStoreById() to return a store
    Store store = new Store();
    when(storeService.getStoreById(1)).thenReturn(store);

    // Mocking productRepository.findById() to return a product
    Product existingProduct = new Product(
      "Product1",
      new BigDecimal("10.0"),
      "image1.jpg",
      store
    );
    when(productRepository.findById(1))
      .thenReturn(Optional.of(existingProduct));

    // Calling the service method to delete a product
    productService.deleteProduct(1, 1);

    // Verifying that productRepository.delete() was called with the correct product
    verify(productRepository, times(1)).delete(existingProduct);
  }
}
