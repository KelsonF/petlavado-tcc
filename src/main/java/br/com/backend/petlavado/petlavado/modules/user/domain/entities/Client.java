package br.com.backend.petlavado.petlavado.modules.user.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name cannot be empty")
    @NotNull
    public String name;

    @NotBlank(message = "The description cannot be empty")
    @NotNull
    public String description;

    @NotBlank(message = "The email cannot be empty")
    @NotNull
    public String email;

    @NotBlank(message = "The phone number cannot be empty")
    @NotNull
    public String phoneNumber;

    @NotBlank(message = "The cnpj cannot be empty")
    @NotNull
    private String cpf;

    public Client(String name, String description, String email, String phoneNumber, String cpf) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
    }

}
