package br.com.backend.petlavado.petlavado.modules.store.domain.entities;

import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products = Collections.emptyList();

    public Store(String storeName, String email, String password, String phoneNumber, String cnpj, UserRole userRole) {
        super(email,password,phoneNumber, userRole);
        this.storeName = storeName;
        this.cnpj = cnpj;
    }
}