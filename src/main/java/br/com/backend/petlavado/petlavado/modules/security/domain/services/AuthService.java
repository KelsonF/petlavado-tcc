package br.com.backend.petlavado.petlavado.modules.security.domain.services;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import br.com.backend.petlavado.petlavado.modules.security.domain.repositories.UserRepository;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String authenticated() {
    return "Hello new user";
  }

  public Collection<User> getUsers() {
    return userRepository.findAll();
  }
}
