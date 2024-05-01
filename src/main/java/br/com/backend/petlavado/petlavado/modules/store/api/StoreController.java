package br.com.backend.petlavado.petlavado.modules.store.api;

import br.com.backend.petlavado.petlavado.modules.store.domain.dtos.StoreDto;
import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import br.com.backend.petlavado.petlavado.modules.store.domain.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/stores")
public class StoreController {
    StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<Store>> listAllStores() {
        Collection<Store> stores = storeService.findAll();

        return ResponseEntity.ok(stores);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Store> createStore(@RequestBody StoreDto dto) {
        Store store = storeService.createStore(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(store);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Store> deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);

        return ResponseEntity.noContent().build();
    }
}