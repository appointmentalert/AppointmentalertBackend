package dev.romahn.model;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@MappedEntity @Data
public class User {

    @Id
    @AutoPopulated
    private UUID id;

    @NotNull
    private String username;

    @NotNull
    private String password;

}
