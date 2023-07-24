package com.Project.CineMille.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Programmazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate dateStart;
    private LocalDate dateEnd;

    @ManyToOne
    private Film film;

}
