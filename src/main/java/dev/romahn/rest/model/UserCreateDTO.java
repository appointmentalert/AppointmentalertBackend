package dev.romahn.rest.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Introspected
public class UserCreateDTO {

    @Email
    private String username;

    @Size(min = 6)
    private String password;
}
