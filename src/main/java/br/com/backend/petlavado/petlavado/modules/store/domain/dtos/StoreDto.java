package br.com.backend.petlavado.petlavado.modules.store.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreDto (
    @NotNull
    @NotBlank(message = "O campo de nome da loja não pode esta vazio")
    String storeName,

    @NotNull
    @NotBlank(message = "O campo de email não pode esta vazio")
    String email,

    @NotNull
    @NotBlank(message = "O campo de senha não pode esta vazio")
    String password,

    @NotNull
    @NotBlank(message = "O campo de telefone não pode esta vazio")
    String phoneNumber,

    @NotNull
    @NotBlank(message = "O campo de cnpj não pode esta vazio")
    String cnpj
){}
