package dev.romahn.rest;

import dev.romahn.model.Alert;
import dev.romahn.repository.AlertRepository;
import dev.romahn.repository.UserRepository;
import dev.romahn.rest.dto.AlertCreate;
import dev.romahn.rest.dto.AlertRead;
import dev.romahn.rest.dto.AlertUpdate;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/alerts")
public class AlertController {

    @Inject
    SecurityService securityService;

    @Inject
    AlertRepository alertRepository;

    @Inject
    UserRepository userRepository;

    @Get()
    public List<AlertRead> get() {
        String username = securityService.username().orElseThrow();
        List<Alert> entities = alertRepository.findByUserUsername(username);

        return entities.stream().map(entity -> {
            AlertRead out = new AlertRead();
            out.setId(entity.getId());
            out.setType(entity.getType());
            out.setEndDate(entity.getEndDate());
            out.setStartDate(entity.getStartDate());
            out.setActive(entity.isActive());
            return out;
        }).toList();
    }

    @Post
    public AlertRead create(@Valid AlertCreate alert) {
        String username = securityService.username().orElseThrow();

        Alert entity = new Alert();
        entity.setUser(userRepository.findByUsername(username).orElseThrow());
        entity.setType(alert.getType());
        entity.setStartDate(alert.getStartDate());
        entity.setEndDate(alert.getEndDate());
        entity.setActive(alert.isActive());

        return createReadDTO(alertRepository.save(entity));
    }

    @Put("/{id}")
    public AlertRead update(@NotNull UUID id, @Valid AlertUpdate alertUpdate) {
        String username = securityService.username().orElseThrow();

        Alert alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alert.setStartDate(alertUpdate.getStartDate());
        alert.setEndDate(alertUpdate.getEndDate());
        alert.setActive(alertUpdate.isActive());

        return createReadDTO(alertRepository.update(alert));
    }

    private AlertRead createReadDTO(Alert entity) {
        AlertRead out = new AlertRead();
        out.setId(entity.getId());
        out.setType(entity.getType());
        out.setStartDate(entity.getStartDate());
        out.setEndDate(entity.getEndDate());
        out.setActive(entity.isActive());
        return out;
    }


    @Delete("/{id}")
    public void delete(@NotNull UUID id) {
        String username = securityService.username().orElseThrow();

        Alert alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alertRepository.delete(alert);
    }

}