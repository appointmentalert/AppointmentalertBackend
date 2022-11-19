package dev.romahn.rest;

import dev.romahn.model.AlertEntity;
import dev.romahn.repository.AlertRepository;
import dev.romahn.repository.UserRepository;
import dev.romahn.rest.model.AlertEntityUpdateDTO;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;

import javax.validation.Valid;
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
    public List<AlertEntity> get() {
         String username = securityService.username().orElseThrow();

         return alertRepository.findByUserUsername(username);
    }

    @Post
    public AlertEntity create(@Valid AlertEntity alert) {
        String username = securityService.username().orElseThrow();

        alert.setUser(userRepository.findByUsername(username).orElseThrow());
        return alertRepository.save(alert);
    }

    @Put("/{id}")
    public AlertEntity update(UUID id, @Valid AlertEntityUpdateDTO alertUpdate) {
        String username = securityService.username().orElseThrow();

        AlertEntity alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alert.setStartDate(alertUpdate.getStartDate());
        alert.setEndDate(alertUpdate.getEndDate());
        alert.setActive(alertUpdate.isActive());

        return alertRepository.update(alert);
    }


    @Delete("/{id}")
    public void delete(UUID id) {
        String username = securityService.username().orElseThrow();

        AlertEntity alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alertRepository.delete(alert);
    }

}