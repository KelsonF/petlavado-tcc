package br.com.backend.petlavado.petlavado.modules.user.domain.entities;

import br.com.backend.petlavado.petlavado.modules.products.domain.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String cnpj;
    @OneToMany
    private List<Product> products;
}
