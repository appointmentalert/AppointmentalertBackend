package dev.romahn.rest.auth;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.inject.Singleton;

@Singleton
public class Argon2PasswordEncoder {

    private final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

    public String encode(String password) {
        return argon2.hash(2, 15*1024, 1, password.toCharArray());
    }

    public boolean matches(String hash, String password) {
         return argon2.verify(hash, password.toCharArray());
    }
}
