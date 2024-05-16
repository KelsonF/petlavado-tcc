package br.com.backend.petlavado.petlavado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.StoreDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.repositories.StoreRepository;
import br.com.backend.petlavado.petlavado.modules.store.domain.services.StoreService;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {
  @Mock
  private StoreRepository storeRepository;

  @InjectMocks
  private StoreService storeService;

  @Test
  void testCreateStore() {
    StoreDto storeDto = new StoreDto(
      "Test Store",
      "test@store.com",
      "password",
      "123456789",
      "12345678901234"
    );
    when(storeRepository.findByEmail(storeDto.email())).thenReturn(null);
    Store store = new Store(
      storeDto.storeName(),
      storeDto.email(),
      storeDto.password(),
      storeDto.phoneNumber(),
      storeDto.cnpj()
    );
    when(storeRepository.save(any(Store.class))).thenReturn(store);

    Store createdStore = storeService.createStore(storeDto);

    assertEquals(storeDto.storeName(), createdStore.getName());
    assertEquals(storeDto.email(), createdStore.getEmail());
    assertEquals(storeDto.password(), createdStore.getPassword());
    assertEquals(storeDto.phoneNumber(), createdStore.getPhoneNumber());
    assertEquals(storeDto.cnpj(), createdStore.getCnpj());
  }

  @Test
  void testFindAll() {
    // Mocking storeRepository.findAll() to return a collection of stores
    when(storeRepository.findAll())
      .thenReturn(
        Arrays.asList(
          new Store(
            "Store1",
            "store1@test.com",
            "password",
            "123456789",
            "12345678901234"
          ),
          new Store(
            "Store2",
            "store2@test.com",
            "password",
            "123456789",
            "12345678901235"
          )
        )
      );

    // Calling the service method to find all stores
    Collection<Store> stores = storeService.findAll();

    // Asserting that the returned collection is not null and contains two stores
    assertNotNull(stores);
    assertEquals(2, stores.size());
  }

  @Test
  void testDeleteStore() {
    // Mocking storeRepository.findById() to return an Optional containing a store
    Store store = new Store(
      "Test Store",
      "test@store.com",
      "password",
      "123456789",
      "12345678901234"
    );
    when(storeRepository.findById(1)).thenReturn(Optional.of(store));

    // Calling the service method to delete the store
    storeService.deleteStore(1);

    // Verifying that storeRepository.delete() was called with the correct store
    verify(storeRepository, times(1)).delete(store);
  }

  @Test
  void testGetStoreById() {
    // Mocking storeRepository.findById() to return an Optional containing a store
    Store store = new Store(
      "Test Store",
      "test@store.com",
      "password",
      "123456789",
      "12345678901234"
    );
    when(storeRepository.findById(1)).thenReturn(Optional.of(store));

    // Calling the service method to get the store by ID
    Store retrievedStore = storeService.getStoreById(1);

    // Asserting that the retrieved store matches the expected store
    assertEquals(store, retrievedStore);
  }
}
