package br.com.backend.petlavado.petlavado.modules.customer.api;

import br.com.backend.petlavado.petlavado.modules.customer.domain.dtos.ClientDto;
import br.com.backend.petlavado.petlavado.modules.customer.domain.entities.Client;
import br.com.backend.petlavado.petlavado.modules.customer.domain.services.ClientService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  ResponseEntity<Client> deleteClient(@PathVariable Integer id) {
    clientService.deleteClient(id);

    return ResponseEntity.noContent().build();
  }
}
