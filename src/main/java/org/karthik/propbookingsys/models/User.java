package org.karthik.propbookingsys.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String mail;
}
