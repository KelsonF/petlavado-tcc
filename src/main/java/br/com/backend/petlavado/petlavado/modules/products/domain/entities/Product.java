package br.com.backend.petlavado.petlavado.modules.products.domain.entities;

import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String description;

    @NotNull
    @Positive
    private Double value;

    private String imageUrl;

    @ManyToOne
    private Store store;

    public Product() {
    }
}