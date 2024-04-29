package br.com.backend.petlavado.petlavado.modules.store.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    private String description;

    @NotNull
    @Positive
    private Double value;

    private String imageUrl;

    @ManyToOne
    private Store store;

    protected Product() {}

    public Product(String description, Double value, String imageUrl, Store store) {
        this.description = description;
        this.value = value;
        this.imageUrl = imageUrl;
        this.store = store;
    }
}
