package br.com.backend.petlavado.petlavado.modules.store.domain.services;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;
import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.StoreDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

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

    public Store createStore(StoreDto data) {
        if (storeRepository.findByEmail(data.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email already in use");
        }

        var encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        return storeRepository.save(
                new Store(
                    data.getStoreName(),
                    data.getEmail(),
                    encryptedPassword,
                    data.getPhoneNumber(),
                    data.getCnpj(),
                    UserRole.STORE
                )
        );
    }

    public void deleteStore(Integer storeId) {
        Store store = getStoreById(storeId);

        storeRepository.delete(store);
    }

    public Store getStoreById(Integer storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
        );
    }
}