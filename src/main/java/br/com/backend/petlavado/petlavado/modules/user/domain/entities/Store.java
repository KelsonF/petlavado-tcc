package br.com.backend.petlavado.petlavado.modules.user.domain.entities;

import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Store {
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
    private String cnpj;

    @OneToMany
    private List<Product> products;

    public Store(String name, String description, String email, String phoneNumber, String cnpj) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cnpj = cnpj;
    }
}
