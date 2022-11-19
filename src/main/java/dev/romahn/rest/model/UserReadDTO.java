package dev.romahn.rest.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.UUID;

@Data
@Introspected
public class UserReadDTO {
    private UUID id;
    private String username;
}
