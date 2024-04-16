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
public class Store extends User {

    @NotBlank(message = "The name cannot be empty")
    @NotNull
    public String storeName;

    @NotBlank(message = "The cnpj cannot be empty")
    @NotNull
    private String cnpj;

    @OneToMany
    private List<Product> products;

    public Store(String storeName, String description, String email, String phoneNumber, String cnpj) {
        super(description, email, phoneNumber);
        this.storeName = storeName;
        this.cnpj = cnpj;
    }
}
