package br.com.backend.petlavado.petlavado.modules.customer.domain.services;

import br.com.backend.petlavado.petlavado.modules.customer.domain.dtos.ClientDto;
import br.com.backend.petlavado.petlavado.modules.customer.domain.entities.Client;
import br.com.backend.petlavado.petlavado.modules.customer.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Collection<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
        );
    }

    public Client createClient(ClientDto data) {
        if (clientRepository.findByEmail(data.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já esta em uso");
        }

        return clientRepository.save(
                new Client(
                        data.getName(),
                        data.getEmail(),
                        data.getPassword(),
                        data.getPhoneNumber(),
                        data.getCpf()
                )
        );
    }

    public void deleteClient(Integer id) {
        Client client = findById(id);

        clientRepository.delete(client);
    }
}
