package br.com.backend.petlavado.petlavado.modules.security.domain.dtos;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;

public record CreateUserDto(
        String name,
        String email,
        String password,
        String phoneNumber,
        UserRole role
) {}
