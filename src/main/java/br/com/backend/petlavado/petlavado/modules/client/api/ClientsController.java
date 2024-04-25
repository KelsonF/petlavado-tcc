package br.com.backend.petlavado.petlavado.modules.client.api;

import br.com.backend.petlavado.petlavado.modules.client.domain.dtos.ClientDto;
import br.com.backend.petlavado.petlavado.modules.client.domain.entities.Client;
import br.com.backend.petlavado.petlavado.modules.client.domain.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Collection<Client>> listAllClients() {
        Collection<Client> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Client> createClient(@RequestBody ClientDto dto) {
        Client client = clientService.createClient(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Client> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}
