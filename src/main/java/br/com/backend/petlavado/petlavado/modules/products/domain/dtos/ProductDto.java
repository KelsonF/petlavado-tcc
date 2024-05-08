package br.com.backend.petlavado.petlavado.modules.products.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDto (
    @NotNull
    @NotBlank(message = "O campo de descrição não pode esta vazio")
    String description,

    @NotNull
    @NotBlank(message = "O campo de valor nao pode esta vazio")
    @Positive
    Double value,

    @NotNull
    @NotBlank(message = "O produto precisa de uma imagem")
    String imageUrl
){}
