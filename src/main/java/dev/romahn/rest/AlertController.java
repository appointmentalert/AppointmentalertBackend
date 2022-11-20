package dev.romahn.rest;

import dev.romahn.model.AlertEntity;
import dev.romahn.repository.AlertRepository;
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
    public List<AlertReadDTO> get() {
        String username = securityService.username().orElseThrow();
        List<AlertEntity> entities = alertRepository.findByUserUsername(username);

        return entities.stream().map(entity -> {
            AlertReadDTO out = new AlertReadDTO();
            out.setId(entity.getId());
            out.setType(entity.getType());
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
        entity.setType(alert.getType());
        entity.setStartDate(alert.getStartDate());
        entity.setEndDate(alert.getEndDate());
        entity.setActive(alert.isActive());

        return createReadDTO(alertRepository.save(entity));
    }

    @Put("/{id}")
    public AlertReadDTO update(@NotNull UUID id, @Valid AlertUpdateDTO alertUpdate) {
        String username = securityService.username().orElseThrow();

        AlertEntity alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alert.setStartDate(alertUpdate.getStartDate());
        alert.setEndDate(alertUpdate.getEndDate());
        alert.setActive(alertUpdate.isActive());

        return createReadDTO(alertRepository.update(alert));
    }

    private AlertReadDTO createReadDTO(AlertEntity entity) {
        AlertReadDTO out = new AlertReadDTO();
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

        AlertEntity alert = alertRepository.findByIdAndUserUsername(id , username).orElseThrow();

        alertRepository.delete(alert);
    }

}