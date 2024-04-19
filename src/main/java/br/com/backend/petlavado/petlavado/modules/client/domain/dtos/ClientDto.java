package br.com.backend.petlavado.petlavado.modules.client.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    @NotNull
    @NotBlank(message = "O campo de nome não pode esta vazio")
    private String name;

    @NotNull
    @NotBlank(message = "O campo de email não pode esta vazio")
    private String email;

    @NotNull
    @NotBlank(message = "O campo de senha não pode esta vazio")
    private String password;

    @NotNull
    @NotBlank(message = "O campo de telefone não pode esta vazio")
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "O campo de cpf não pode esta vazio")
    private String cpf;
}
