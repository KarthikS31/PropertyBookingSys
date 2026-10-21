package org.karthik.propbookingsys.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PropertyTypes {
    @Id
    Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PropTypes type;
    String description;

    @Column(nullable = false)
    int capacity;


    enum PropTypes{
        SMALL,
        MEDIUM,
        LARGE
    }
}
