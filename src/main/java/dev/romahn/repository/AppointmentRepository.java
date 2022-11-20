package dev.romahn.repository;

import dev.romahn.model.AlertType;
import dev.romahn.model.Appointment;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AppointmentRepository extends CrudRepository<Appointment, UUID> {
    List<Appointment> findByType(AlertType type);
}