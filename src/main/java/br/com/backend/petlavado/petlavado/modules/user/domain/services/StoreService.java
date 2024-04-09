package br.com.backend.petlavado.petlavado.modules.user.domain.services;

import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.user.domain.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    public Optional<Store> getStoreOrNull(Integer storeId){
        return storeRepository.findById(storeId);
    }
}
