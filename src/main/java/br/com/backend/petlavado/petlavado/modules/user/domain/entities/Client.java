package br.com.backend.petlavado.petlavado.modules.user.domain.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client extends User {
  @NotBlank(message = "The name cannot be empty")
  @NotNull
  public String name;

  @NotBlank(message = "The cnpj cannot be empty")
  @NotNull
  private String cpf;

  public Client(String name, String description, String email, String phoneNumber, String cpf) {
    super(description, email, phoneNumber);
    this.name = name;
    this.cpf = cpf;
  }
}