package dev.romahn.repository;

import dev.romahn.model.AlertEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlertRepository extends CrudRepository<AlertEntity, Long> {
}