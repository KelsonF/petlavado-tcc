package br.com.backend.petlavado.petlavado.modules.security.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "The password cannot be empty")
    @NotNull
    private String password;

    @NotBlank(message = "The email cannot be empty")
    @NotNull
    private String email;

    @NotBlank(message = "The phone number cannot be empty")
    @NotNull
    private String phoneNumber;

    @NotNull
    private UserRole userRole;

    @NotNull
    private String geolocation;

    public User(String email, String password, String phoneNumber, UserRole userRole, String geolocation) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.geolocation = geolocation;
    }

    public String getIdString() {
        return this.id.toString();
    }
}