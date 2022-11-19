package dev.romahn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@MappedEntity @Data
public class UserEntity {

    @Id
    @AutoPopulated
    private UUID id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
