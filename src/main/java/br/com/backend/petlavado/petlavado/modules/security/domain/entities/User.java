package br.com.backend.petlavado.petlavado.modules.security.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User implements UserDetails {
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

    public User(String name,String email, String password, String phoneNumber, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (userRole){
            case STORE -> authorities.add(new SimpleGrantedAuthority("ROLE_STORE"));
            case CLIENT -> authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }

        return authorities;
    }

    @Transient
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}