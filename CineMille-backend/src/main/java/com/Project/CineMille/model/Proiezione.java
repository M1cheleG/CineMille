package com.Project.CineMille.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Proiezione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime date;

    @ManyToOne
    private Programmazione programmazione;
    @ManyToOne
    private Sala sala;
}
