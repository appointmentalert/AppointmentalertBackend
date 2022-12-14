package dev.romahn.rest.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Introspected
public class UserCreate {

    @Email
    private String username;

    @Size(min = 6)
    private String password;
}
