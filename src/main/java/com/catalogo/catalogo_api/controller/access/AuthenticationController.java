package com.catalogo.catalogo_api.controller.access;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.catalogo.catalogo_api.model.access.User;
import com.catalogo.catalogo_api.model.access.UserService;
import com.catalogo.catalogo_api.model.security.JwtService;


@RestController
@RequestMapping("/controller/access")
@CrossOrigin
public class AuthenticationController {

   private final JwtService jwtService;
   
   private UserService userService;

   public AuthenticationController(JwtService jwtService, UserService userService) {
       this.jwtService = jwtService;
       this.userService = userService;
   }
   @PostMapping
   public Map<Object, Object> signin(@RequestBody AuthenticationRequest data) {
   
       User authenticatedUser = userService.authenticate(data.getUsername(), data.getPassword());
       String jwtToken = jwtService.generateToken(authenticatedUser);
       Map<Object, Object> loginResponse = new HashMap<>();
       loginResponse.put("username", authenticatedUser.getUsername());
       loginResponse.put("token", jwtToken);
       loginResponse.put("tokenExpiresIn", jwtService.getExpirationTime());
       return loginResponse;
   }    
}
