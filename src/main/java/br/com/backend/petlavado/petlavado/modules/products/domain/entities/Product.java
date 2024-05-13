package br.com.backend.petlavado.petlavado.modules.products.domain.entities;

import br.com.backend.petlavado.petlavado.modules.store.domain.entities.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotEmpty
    private String description;

    @NotNull
    @Positive
    private BigDecimal value;

    private String imageUrl;

    @ManyToOne
    private Store store;

    protected Product() {}

    public Product(String description, BigDecimal value, String imageUrl, Store store) {
        this.description = description;
        this.value = value;
        this.imageUrl = imageUrl;
        this.store = store;
    }
}
