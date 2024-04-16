package br.com.backend.petlavado.petlavado.modules.user.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "The description cannot be empty")
  @NotNull
  public String description;

  @NotBlank(message = "The email cannot be empty") 
  @NotNull
  private String email;

  @NotBlank(message = "The phone number cannot be empty")
  @NotNull
  public String phoneNumber;

  public User(String description,String email, String phoneNumber){
    this.description = description;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}