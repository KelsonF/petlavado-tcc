package br.com.backend.petlavado.petlavado.modules.security.domain.services;

import br.com.backend.petlavado.petlavado.modules.security.domain.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.secret.key}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("name", user.getName())
                    .withClaim("email", user.getEmail())
                    .withClaim("roles", user.getUserRole().name())
                    .withIssuer("pet-lavado-api")
                    .withExpiresAt(this.generateExpirationTime())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("JWT generation failed");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT
                    .require(algorithm)
                    .withIssuer("pet-lavado-api")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            throw new RuntimeException("JWT verification failed");
        }
    }

    private Date generateExpirationTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusHours(4);
        return Date.from(expirationTime.toInstant(ZoneOffset.UTC));
    }
}
