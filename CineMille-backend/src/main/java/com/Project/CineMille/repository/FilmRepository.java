package com.Project.CineMille.repository;

import com.Project.CineMille.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "Select film.* from film inner join programmazione on film.id = programmazione.film_id \n" +
            " where date_start <= :date AND date_end >= :date", nativeQuery = true)
    List<Film> findFilmCorrente(@Param("date") LocalDate date);

    @Query(value = "Select film.* from film inner join programmazione on film.id = programmazione.film_id \n" +
            "where date_end <:date", nativeQuery = true)
    List<Film> findFilmStorico(@Param("date") LocalDate date);

    @Query(value = "Select film.* from film inner join programmazione on film.id = programmazione.film_id \n" +
            "where (date_start BETWEEN :first AND :second) OR (date_end BETWEEN :first AND :second)  OR (date_start<=:first AND date_end>=:second)", nativeQuery = true)
    List<Film> findFilmQuery(@Param("first") LocalDate first, @Param("second") LocalDate second);
}
