package br.com.backend.petlavado.petlavado.modules.client.domain.entities;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client extends User {
    @NotBlank(message = "The name cannot be empty")
    @NotNull
    public String name;

    @NotBlank(message = "The cpf cannot be empty")
    @NotNull
    private String cpf;

    protected Client() {}

    public Client(
        String name, 
        String email, 
        String password, 
        String phoneNumber, 
        String cpf, 
        UserRole userRole
    ) {
        super(email, password, phoneNumber, userRole);
        this.name = name;
        this.cpf = cpf;
    }
}
