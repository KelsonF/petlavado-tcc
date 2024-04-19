package br.com.backend.petlavado.petlavado.modules.store.domain.services;

import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Optional<Store> getStoreOrNull(UUID storeId) {
        return storeRepository.findById(storeId);
    }
}