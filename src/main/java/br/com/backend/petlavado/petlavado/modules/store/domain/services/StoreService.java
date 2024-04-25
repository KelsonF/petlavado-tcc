package br.com.backend.petlavado.petlavado.modules.store.domain.services;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;
import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.StoreDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Collection<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store createStore(StoreDto storeDto) {
        if (storeRepository.findByEmail(storeDto.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email already in use");
        }

        return storeRepository.save(
                new Store(
                        storeDto.getStoreName(),
                        storeDto.getEmail(),
                        storeDto.getPassword(),
                        storeDto.getPhoneNumber(),
                        storeDto.getCnpj(),
                        UserRole.STORE
                )
        );
    }

    public void deleteStore(UUID storeId) {
        Store store = getStoreById(storeId);

        storeRepository.delete(store);
    }

    public Store getStoreById(UUID storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
        );
    }
}