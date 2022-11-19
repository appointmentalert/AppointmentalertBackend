package dev.romahn.rest;

import dev.romahn.model.AlertTypeEntity;
import dev.romahn.repository.AlertTypeRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

@Controller("/api/types")
@PermitAll
public class TypeController {

    @Inject
    AlertTypeRepository alertTypeRepository;

    @Get()
    public Iterable<AlertTypeEntity> get() {
        return alertTypeRepository.findAll();
    }
}