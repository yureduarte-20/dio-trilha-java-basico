package com.yure.complaints.application.controllers;

import com.yure.complaints.application.request.LoginResquest;
import com.yure.complaints.application.request.RegisterRequest;
import com.yure.complaints.application.services.TokenService;
import com.yure.complaints.domain.models.Roles;
import com.yure.complaints.domain.models.User;
import com.yure.complaints.domain.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    public AuthorizationController(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder encoder, TokenService tokenService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginResquest login) {
        var uPass = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        try {
            var auth = this.authenticationManager.authenticate(uPass);
            System.out.println(auth.getCredentials());
            var token = this.tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok().body(new Object() {
                public String accessToken = token;
            });
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Object() {
                public String message = e.getMessage();
            });
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest req) {
        if (this.userRepository.findByEmail(req.email()).isPresent())
            return ResponseEntity.unprocessableEntity().body(new Object(){
                public String  message = "Email já cadastrado";
            });
        var user = new User();
        user.setEmail(req.email());
        user.setName(req.name());
        user.setPassword(this.encoder.encode(req.password()));
        user.setRole(Roles.USER);
        var data = this.userRepository.save(user);
        return ResponseEntity.ok(new Object() {
            public Long id = data.getId();
            public String email = data.getUsername();
        });
    }
}
