package com.catalogo.catalogo_api.model.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogo.catalogo_api.model.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Autowired
    private JwtService jwtService;


  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder) {

    this.authenticationManager = authenticationManager;
    this.repository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User authenticate(String username, String password) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));

    return repository.findByUsername(username).orElseThrow();
  }

  @Transactional
  public User findByUsername(String username) {

    return repository.findByUsername(username).get();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return repository.findByUsername(username).get();
  }

  @Transactional
  public User save(User user) {

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(Boolean.TRUE);
    return repository.save(user);
  }

  public User obterUsuarioLogado(HttpServletRequest request) {

      User usuarioLogado = null;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {

            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);
            usuarioLogado = findByUsername(userEmail);
            return usuarioLogado;
        }

        return usuarioLogado;
    }

}
