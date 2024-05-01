package br.com.backend.petlavado.petlavado.modules.security.domain.dtos;

import lombok.Getter;

@Getter
public class UserDto {
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean isAdmin = false;
}
