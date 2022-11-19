package dev.romahn.repository;

import dev.romahn.model.AlertTypeEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlertTypeRepository extends CrudRepository<AlertTypeEntity, Long> {
}
