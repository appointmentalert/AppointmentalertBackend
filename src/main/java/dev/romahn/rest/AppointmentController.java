package dev.romahn.rest;

import dev.romahn.model.Alert;
import dev.romahn.model.AlertType;
import dev.romahn.model.Appointment;
import dev.romahn.model.User;
import dev.romahn.repository.AlertRepository;
import dev.romahn.repository.AppointmentRepository;
import dev.romahn.repository.UserRepository;
import dev.romahn.rest.dto.AppointmentListUpdate;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Controller("/appointments")
@PermitAll
@Slf4j
public class AppointmentController {

    @Inject
    AppointmentRepository appointmentRepository;

    @Inject
    AlertRepository alertRepository;

    @Inject
    UserRepository userRepository;

    @Post
    public void update(List<AppointmentListUpdate> appointmentListUpdates) {
        for (AppointmentListUpdate appointmentListUpdate : appointmentListUpdates) {

            AlertType type = appointmentListUpdate.getType();

            List<Appointment> stale = appointmentRepository.findByType(type);
            List<LocalDate> staleDates = stale.stream().map(Appointment::getDate).toList();
            List<LocalDate> freshDates = appointmentListUpdate.getAppointments();

            List<Appointment> toDelete = stale.stream().filter(a -> !freshDates.contains(a.getDate())).toList();
            List<LocalDate> toCreate = freshDates.stream().filter(a -> !staleDates.contains(a)).toList();

            toDelete.forEach(a -> log.info("Deleting Appointment on {} for {}", a.getDate(), type));
            appointmentRepository.deleteAll(toDelete);

            toCreate.forEach(a -> log.info("Creating Appointment on {} for {}", a, type));
            appointmentRepository.saveAll(toCreate.stream().map(a -> new Appointment(type, a)).toList());

            for (LocalDate appointment : toCreate) {
                List<Alert> alerts = alertRepository.findByTypeAndActiveTrue(type);

                List<Alert> toNotify = alerts.stream().filter(alert ->
                                appointment.isAfter(alert.getStartDate().minusDays(1)) &&
                                appointment.isBefore(alert.getEndDate().plusDays(1)))
                                .toList();

                toNotify.forEach(alert -> {

                    User user = userRepository.findById(alert.getUser().getId()).orElseThrow();
                    log.info("Notify {} of {} at {}", user.getUsername() , type.getDescription(), appointment);
                });
            }

        }
    }

}