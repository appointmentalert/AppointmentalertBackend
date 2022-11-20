package dev.romahn.rest;

import dev.romahn.model.User;
import dev.romahn.repository.UserRepository;
import dev.romahn.rest.dto.UserCreate;
import dev.romahn.rest.dto.UserRead;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.Optional;

@Controller("/api/signup")
@PermitAll
public class UserController {

    PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    @Inject
    UserRepository userRepository;

    @Post
    public UserRead signup(@Valid UserCreate user) {

        Optional<User> result = userRepository.findByUsername(user.getUsername());

        if (result.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User entity = new User();
        entity.setUsername(user.getUsername());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        User created = userRepository.save(entity);

        UserRead out = new UserRead();
        out.setId(created.getId());
        out.setUsername(created.getUsername());

        return out;
    }

}

