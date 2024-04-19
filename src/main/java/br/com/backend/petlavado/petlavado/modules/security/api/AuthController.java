package br.com.backend.petlavado.petlavado.modules.security.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.petlavado.petlavado.modules.security.domain.services.AuthService;

@RestController
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping
  public String getTolkien() {
    return authService.authenticated();
  }
}
