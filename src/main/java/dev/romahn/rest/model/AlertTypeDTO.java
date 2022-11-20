package dev.romahn.rest.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class AlertTypeDTO {
    private String code;
    private String description;
}
