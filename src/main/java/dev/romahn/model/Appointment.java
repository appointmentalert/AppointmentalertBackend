package dev.romahn.model;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@MappedEntity @Data @NoArgsConstructor
public class Appointment {

    public Appointment(AlertType type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    @Id
    @AutoPopulated
    private UUID id;

    @NotNull
    private AlertType type;

    @NotNull
    private LocalDate date;

}
