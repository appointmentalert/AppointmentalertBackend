package dev.romahn.rest.auth;

import dev.romahn.model.UserEntity;
import dev.romahn.repository.UserRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Optional;


@Singleton
public class AuthProviderUserPassword implements AuthenticationProvider {

    @Inject
    UserRepository userRepository;

    @Inject
    Argon2PasswordEncoder passwordEncoder;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {

            String username = (String) authenticationRequest.getIdentity();
            String password = (String) authenticationRequest.getSecret();

            if (!StringUtils.hasText(username)) {
                emitter.error(new NullPointerException("username cannot be empty"));
            }

            if (!StringUtils.hasText(password)) {
                emitter.error(new NullPointerException("password cannot be empty"));
            }

            username = username.toLowerCase();
            Optional<UserEntity> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                if (passwordEncoder.matches(user.get().getPassword(), password)) {
                    emitter.next(AuthenticationResponse.success(username));
                    emitter.complete();
                } else {
                    emitter.error(AuthenticationResponse.exception("invalid password"));
                }
            } else {
                emitter.error(AuthenticationResponse.exception("user not found"));
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
