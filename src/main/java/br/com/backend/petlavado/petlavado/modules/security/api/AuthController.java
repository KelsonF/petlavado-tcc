package br.com.backend.petlavado.petlavado.modules.security.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.backend.petlavado.petlavado.modules.security.domain.dtos.UserDto;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.UserRole;
import br.com.backend.petlavado.petlavado.modules.security.domain.repositories.UserRepository;
import br.com.backend.petlavado.petlavado.modules.security.domain.services.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  
  private final AuthService authService;
  private final UserRepository userRepository;

  public AuthController(AuthService authService, UserRepository userRepository) {
    this.authService = authService;
    this.userRepository = userRepository;
  }

  @PostMapping("login")
  public ResponseEntity<String> login(@RequestBody @Valid UserDto userDto) {
    if (userRepository.findbyEmail(userDto.getEmail()) != null) {
      return ResponseEntity.badRequest().body("Email ja utilizado");
    }

    var encryptedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());

    UserRole role = userDto.getIsAdmin() ? UserRole.STORE : UserRole.CLIENT;
    var newUser = new User(
      userDto.getEmail(),
      encryptedPassword,
      userDto.getPhoneNumber(),
      role
    );

    User savedUser = userRepository.save(newUser);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(savedUser.getId())
      .toUri();
    
    return ResponseEntity.created(location).build();
  }

  @GetMapping
  public String getTolkien() {
    return authService.authenticated();
  }
}
