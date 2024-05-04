package br.com.backend.petlavado.petlavado.modules.security.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "The name cannot be empty")
    @NotNull
    private String name;

    @NotBlank(message = "The email cannot be empty")
    @NotNull
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @NotNull
    private String password;

    @NotBlank(message = "The phone number cannot be empty")
    @NotNull
    private String phoneNumber;

    @NotNull
    private UserRole userRole;

    @NotNull
    private String geoLocation;

    public User(String name,String email, String password, String phoneNumber, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.geoLocation = "";
    }

    public String getIdString() {
        return this.id.toString();
    }

    public void setNewGeoLocation(String location) {
        if (!this.geoLocation.equals(location)) {
            this.geoLocation = location;
        }
    }
    

    public Collection<? extends GrantedAuthority> getAuthoraties() {
        if (this.userRole == UserRole.STORE)
            return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}