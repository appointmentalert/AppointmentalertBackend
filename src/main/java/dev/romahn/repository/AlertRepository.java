package dev.romahn.repository;

import dev.romahn.model.Alert;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlertRepository extends CrudRepository<Alert, UUID> {

    List<Alert> findByUserUsername(String username);

    Optional<Alert> findByIdAndUserUsername(UUID id, String username);
}