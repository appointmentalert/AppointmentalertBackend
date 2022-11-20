package dev.romahn.rest.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.UUID;

@Data
@Introspected
public class UserRead {
    private UUID id;
    private String username;
}
