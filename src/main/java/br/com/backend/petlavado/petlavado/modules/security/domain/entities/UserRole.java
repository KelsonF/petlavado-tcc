package br.com.backend.petlavado.petlavado.modules.security.domain.entities;

public enum UserRole {
    CLIENT("cliente"),
    STORE("loja");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
