package dev.redradish.investai.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

/**
 * @author nkucherenko
 */
@Getter
@Setter
@Entity
public class Portfolio {
    @Id
    @UuidGenerator
    private UUID id;
    private String tinkoffId;
    private byte profitability;
    @OneToMany
    private List<ActionRecord> actionHistory;
}
