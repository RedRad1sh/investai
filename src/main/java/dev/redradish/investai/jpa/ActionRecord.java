package dev.redradish.investai.jpa;

import dev.redradish.investai.integration.perplexity.dto.TinkoffCommandDto;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

/**
 * @author nkucherenko
 */
@Entity
@Getter
@Setter
public class ActionRecord {
    @Id
    @UuidGenerator
    private UUID uuid;

    @JdbcTypeCode(SqlTypes.JSON)
    private TinkoffCommandDto command;

    private UserAcceptation acceptation;

    @ManyToOne
    private Portfolio portfolio;
}
