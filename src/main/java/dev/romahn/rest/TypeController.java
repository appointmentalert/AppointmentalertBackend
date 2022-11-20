package dev.romahn.rest;

import dev.romahn.model.AlertType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.annotation.security.PermitAll;

import java.util.HashMap;
import java.util.Map;

@Controller("/api/types")
@PermitAll
public class TypeController {

    @Get()
    public Map<String, String> list() {
        Map<String, String> result = new HashMap<>();

        for (AlertType type : AlertType.values()) {
            result.put(type.name(), type.getDescription());
        }

        return result;
    }
}