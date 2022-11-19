package dev.romahn.rest;

import dev.romahn.model.AlertEntity;
import dev.romahn.repository.AlertRepository;
import dev.romahn.repository.AlertTypeRepository;
import dev.romahn.repository.UserRepository;
import dev.romahn.rest.model.AlertCreateDTO;
import dev.romahn.rest.model.AlertReadDTO;
import dev.romahn.rest.model.AlertUpdateDTO;
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
    AlertTypeRepository alertTypeRepository;

    @Inject
    UserRepository userRepository;

    @Get()
    public List<AlertReadDTO> get() {
        String username = securityService.username().orElseThrow();
        List<AlertEntity> entities = alertRepository.findByUserUsername(username);

        return entities.stream().map(entity -> {
            AlertReadDTO out = new AlertReadDTO();
            out.setId(entity.getId());
            out.setTypeId(entity.getType().getId());
            out.setEndDate(entity.getEndDate());
            out.setStartDate(entity.getStartDate());
            out.setActive(entity.isActive());
            return out;
        }).toList();
    }

    @Post
    public AlertReadDTO create(@Valid AlertCreateDTO alert) {
        String username = securityService.username().orElseThrow();

        AlertEntity entity = new AlertEntity();
        entity.setUser(userRepository.findByUsername(username).orElseThrow());
        entity.setType(alertTypeRepository.findById(alert.getTypeId()).orElseThrow());
        entity.setStartDate(alert.getStartDate());
        entity.setEndDate(alert.getEndDate());
        entity.setActive(alert.isActive());

        AlertEntity created = alertRepository.save(entity);

        AlertReadDTO out = new AlertReadDTO();
        out.setId(created.getId());
        out.setTypeId(created.getType().getId());
        out.setStartDate(created.getStartDate());
        out.setEndDate(created.getEndDate());
        out.setActive(created.isActive());

        return out;
    }

    @Put("/{id}")
    public AlertReadDTO update(UUID id, @Valid AlertUpdateDTO alertUpdate) {
        String username = securityService.username().orElseThrow();

        AlertEntity alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alert.setStartDate(alertUpdate.getStartDate());
        alert.setEndDate(alertUpdate.getEndDate());
        alert.setActive(alertUpdate.isActive());

        AlertEntity updated = alertRepository.update(alert);

        AlertReadDTO out = new AlertReadDTO();
        out.setId(updated.getId());
        out.setTypeId(updated.getType().getId());
        out.setStartDate(updated.getStartDate());
        out.setEndDate(updated.getEndDate());
        out.setActive(updated.isActive());

        return out;
    }


    @Delete("/{id}")
    public void delete(UUID id) {
        String username = securityService.username().orElseThrow();

        AlertEntity alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alertRepository.delete(alert);
    }

}