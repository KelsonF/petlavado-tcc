package br.com.backend.petlavado.petlavado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PetLavadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetLavadoApplication.class, args);
    }

}
