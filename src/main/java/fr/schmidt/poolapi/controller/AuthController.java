package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.AuthRequest;
import fr.schmidt.poolapi.dto.response.AuthResponse;
import fr.schmidt.poolapi.security.JwtService;
import fr.schmidt.poolapi.service.PersonDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PersonDetailsService personDetailsService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request){
        // Vérif de l'email + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        // charge l'utilisateur
        UserDetails userDetails = personDetailsService.loadUserByUsername(request.email());
        // génération et retour du token
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

}
