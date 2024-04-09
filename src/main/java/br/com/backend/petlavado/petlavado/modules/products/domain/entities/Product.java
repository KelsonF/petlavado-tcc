package br.com.backend.petlavado.petlavado.modules.products.domain.entities;

import br.com.backend.petlavado.petlavado.modules.user.domain.entities.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private Double value;
    private String imageUrl;
    @ManyToOne
    private Store store;
}
