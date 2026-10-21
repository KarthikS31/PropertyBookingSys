package org.karthik.propbookingsys.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Lob
    byte[] image;
    @Enumerated(EnumType.STRING)
    Status status;
    @Column(nullable = false)
    BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "proptype")
    PropertyTypes propType;

    public enum Status {
        AVAILABLE,
        MAINTENANCE,
        RESERVED
    }
}
