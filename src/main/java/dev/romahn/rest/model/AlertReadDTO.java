package dev.romahn.rest.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Introspected
public class AlertReadDTO {
    private UUID id;
    private UUID typeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
