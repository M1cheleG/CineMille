package com.Project.CineMille.repository;

import com.Project.CineMille.model.Proiezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProiezioneRepository extends JpaRepository<Proiezione, Long> {


    @Query(value = "SELECT proiezione.* FROM proiezione inner join programmazione on proiezione.programmazione_id = programmazione.id\n" +
            " where date_start <= :date AND date_end >= :date", nativeQuery = true)
    List<Proiezione> findProiezioneCorrente(@Param("date") LocalDate date);

    @Query(value = "SELECT proiezione.* FROM proiezione inner join programmazione on proiezione.programmazione_id = programmazione.id\n" +
            "where date_end < :date", nativeQuery = true)
    List<Proiezione> findProiezioneStorico(@Param("date") LocalDate date);

    @Query(value = "SELECT proiezione.* FROM proiezione inner join programmazione on proiezione.programmazione_id = programmazione.id\n" +
            "where (date_start BETWEEN :first AND :second) OR (date_end BETWEEN :first AND :second)  OR (date_start<=:first AND date_end>=:second)", nativeQuery = true)
    List<Proiezione> findProiezioneQuery(@Param("first") LocalDate first, @Param("second") LocalDate second);
}