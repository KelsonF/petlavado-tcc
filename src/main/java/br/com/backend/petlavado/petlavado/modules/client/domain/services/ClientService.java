package br.com.backend.petlavado.petlavado.modules.client.domain.services;

import br.com.backend.petlavado.petlavado.modules.client.domain.dtos.ClientDto;
import br.com.backend.petlavado.petlavado.modules.client.domain.entities.Client;
import br.com.backend.petlavado.petlavado.modules.client.domain.repositories.ClientRepository;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

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
        
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        return clientRepository.save(
                new Client(
                    data.getName(),
                    data.getEmail(),
                    encryptedPassword,
                    data.getPhoneNumber(),
                    data.getCpf(),
                    UserRole.CLIENT
                )
        );
    }

    public void deleteClient(Integer id) {
        Client client = findById(id);

        clientRepository.delete(client);
    }
}
