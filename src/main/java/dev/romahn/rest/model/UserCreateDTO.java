package dev.romahn.rest.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Introspected
public class UserCreateDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
