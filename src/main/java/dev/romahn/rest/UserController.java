package dev.romahn.rest;

import dev.romahn.model.UserEntity;
import dev.romahn.repository.UserRepository;
import dev.romahn.rest.auth.Argon2PasswordEncoder;
import dev.romahn.rest.model.UserCreateDTO;
import dev.romahn.rest.model.UserReadDTO;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.Optional;

@Controller("/api/signup")
@PermitAll
public class UserController {

    @Inject
    UserRepository userRepository;

    @Inject
    Argon2PasswordEncoder passwordEncoder;

    @Post
    public UserReadDTO signup(@Valid UserCreateDTO user) {

        Optional<UserEntity> result = userRepository.findByUsername(user.getUsername());

        if (result.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity created = userRepository.save(entity);

        UserReadDTO out = new UserReadDTO();
        out.setId(created.getId());
        out.setUsername(created.getUsername());

        return out;
    }

}

