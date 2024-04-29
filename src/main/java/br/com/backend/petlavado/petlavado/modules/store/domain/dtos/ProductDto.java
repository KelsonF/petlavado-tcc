package br.com.backend.petlavado.petlavado.modules.store.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull
    @NotBlank(message = "O campo de descrição não pode esta vazio")
    String description;

    @NotNull
    @NotBlank(message = "O campo de valor nao pode esta vazio")
    @Positive
    Double value;

    @NotNull
    @NotBlank(message = "O produto precisa de uma imagem")
    String imageUrl;
}
