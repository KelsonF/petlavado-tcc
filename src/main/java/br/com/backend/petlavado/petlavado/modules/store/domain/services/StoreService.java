package br.com.backend.petlavado.petlavado.modules.store.domain.services;

import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.StoreDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.repositories.StoreRepository;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StoreService {
  private final StoreRepository storeRepository;

  public StoreService(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  public Collection<Store> findAll() {
    return storeRepository.findAll();
  }

  public Store createStore(StoreDto storeDto) {
    if (storeRepository.findByEmail(storeDto.email()) != null) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Email already in use"
      );
    }

    return storeRepository.save(
      new Store(
        storeDto.storeName(),
        storeDto.email(),
        storeDto.password(),
        storeDto.phoneNumber(),
        storeDto.cnpj()
      )
    );
  }

  public void deleteStore(Integer storeId) {
    Store store = getStoreById(storeId);

    storeRepository.delete(store);
  }

  public Store getStoreById(Integer storeId) {
    return storeRepository
      .findById(storeId)
      .orElseThrow(
        () ->
          new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
      );
  }
}
