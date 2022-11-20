package dev.romahn.rest.model;

import dev.romahn.model.AlertType;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Introspected
public class AlertReadDTO {
    private UUID id;
    private AlertType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
