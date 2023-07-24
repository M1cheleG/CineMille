package com.Project.CineMille.repository;

import com.Project.CineMille.model.Programmazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgrammazioneRepository extends JpaRepository<Programmazione, Long> {

    @Query(value = "Select * from programmazione \n" +
            " where date_start <= :date AND date_end >= :date", nativeQuery = true)
    List<Programmazione> findProgrammazioneCorrente(@Param("date") LocalDate date);

    @Query(value = "Select * from programmazione \n" +
            "where date_end < :date", nativeQuery = true)
    List<Programmazione> findProgrammazioneStorico(@Param("date") LocalDate date);


    @Query(value = "Select * from programmazione \n" +
            "where (date_start BETWEEN :first AND :second) OR (date_end BETWEEN :first AND :second)  OR (date_start<=:first AND date_end>=:second)", nativeQuery = true)
    List<Programmazione> findProgrammazioneQuery(@Param("first") LocalDate first, @Param("second") LocalDate second);
}
