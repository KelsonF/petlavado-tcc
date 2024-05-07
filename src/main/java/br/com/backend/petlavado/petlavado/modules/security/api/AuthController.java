package br.com.backend.petlavado.petlavado.modules.security.api;

import br.com.backend.petlavado.petlavado.modules.security.domain.dtos.CreateUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.petlavado.petlavado.modules.security.domain.dtos.UserDto;
import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import br.com.backend.petlavado.petlavado.modules.security.domain.repositories.UserRepository;
import br.com.backend.petlavado.petlavado.modules.security.domain.services.TokenService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("login")
  public ResponseEntity<String> login(@RequestBody @Valid UserDto data) {
    User user = userRepository.loadUserByEmaill(data.email());

    if (passwordEncoder.matches(user.getPassword(), data.password())) {
      String token = tokenService.generateToken(user);
      return ResponseEntity.ok(token);
    }

    return ResponseEntity.badRequest().build();
  }

  @PostMapping("register")
  public ResponseEntity<String> register(@RequestBody @Valid CreateUserDto data) {
    if (userRepository.loadUserByEmaill(data.email()) != null) {
      return ResponseEntity.badRequest().body("Email ja utilizado");
    }

    var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

    var newUser = new User(
        data.name(),
        data.email(),
        encryptedPassword,
        data.phoneNumber(),
        data.role());

    userRepository.save(newUser);
    String token = tokenService.generateToken(newUser);
    
    return ResponseEntity.ok(token);
  }
}
