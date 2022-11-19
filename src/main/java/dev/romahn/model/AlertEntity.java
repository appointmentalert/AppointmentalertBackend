package dev.romahn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@MappedEntity @Data
public class AlertEntity {

    @Id
    @AutoPopulated
    private UUID id;

    @JsonIgnore
    private UserEntity user;

    @NotNull
    private AlertTypeEntity type;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private boolean active = true;

}
