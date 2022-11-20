package dev.romahn.rest;

import dev.romahn.model.AlertType;
import dev.romahn.rest.model.AlertTypeDTO;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.annotation.security.PermitAll;

import java.util.Arrays;
import java.util.List;

@Controller("/api/types")
@PermitAll
public class TypeController {

    @Get()
    public List<AlertTypeDTO> list() {
        return Arrays.stream(AlertType.values()).map(alertType -> {
            AlertTypeDTO dto = new AlertTypeDTO();
            dto.setCode(alertType.name());
            dto.setDescription(alertType.getDescription());
            return dto;
        }).toList();
    }
}