package br.com.backend.petlavado.petlavado.modules.security.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.backend.petlavado.petlavado.modules.security.domain.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  public String authenticated() {
    return "Hello new user";
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findbyEmail(username);
  }
}
