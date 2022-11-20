package dev.romahn.rest.dto;

import dev.romahn.model.AlertType;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Introspected
public class AppointmentListUpdate {
    @NotNull
    private AlertType type;

    @NotNull
    private List<LocalDate> appointments;

}
