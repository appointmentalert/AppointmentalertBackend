package dev.romahn.rest.dto;

import dev.romahn.model.AlertType;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Introspected
public class AlertCreate {

    @NotNull
    private AlertType type;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private boolean active;
}
