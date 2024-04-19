package br.com.backend.petlavado.petlavado.modules.security.domain.services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
  public String authenticated() {
    return "Hello new user";
  }
}
