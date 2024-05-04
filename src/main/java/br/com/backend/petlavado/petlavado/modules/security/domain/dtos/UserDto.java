package br.com.backend.petlavado.petlavado.modules.security.domain.dtos;

public record UserDto(
        String name,
        String email,
        String password,
        String phoneNumber,
        Boolean isAdmin
){
    public UserDto(String name, String email, String password, String phoneNumber){
        this(name, email, password, phoneNumber, false);
    }
}
