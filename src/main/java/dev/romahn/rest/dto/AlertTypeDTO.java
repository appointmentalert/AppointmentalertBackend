package dev.romahn.rest.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class AlertTypeDTO {
    private String code;
    private String description;
}
