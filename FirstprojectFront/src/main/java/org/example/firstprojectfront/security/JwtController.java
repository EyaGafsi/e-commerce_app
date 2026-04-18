package org.example.firstprojectfront.security;

import org.example.firstprojectfront.entities.User;
import org.example.firstprojectfront.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class JwtController {

        @Autowired
        public AuthenticationManager authenticationManager;

        @Autowired
        public JwtService jwtService;

        @Autowired
        public UserRepository userRepository;

        @PostMapping("/authenticate")
        public JwtResponse authenticate(@Valid @RequestBody JwtRequest jwtRequest) {

                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                jwtRequest.getUsername(),
                                                jwtRequest.getPassword()));

                UserDetails userDetails = jwtService.loadUserByUsername(jwtRequest.getUsername());

                String token = jwtService.generateToken(userDetails);

                User user = userRepository
                                .findByUsername(jwtRequest.getUsername())
                                .orElseThrow();

                return new JwtResponse(user, token);
        }
}
