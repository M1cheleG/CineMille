package com.Project.CineMille.service;

import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.repository.ProgrammazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammazioneService {

    @Autowired
    ProgrammazioneRepository programmazioneRepository;

    public void addProgrammazione(Programmazione programmazione) {
        programmazioneRepository.save(programmazione);
    }

    public void updateProgrammazione(Programmazione programmazione) {
        programmazioneRepository.save(programmazione);
    }

    public Optional<Programmazione> findByID(long id) {
        Optional<Programmazione> programmazione = programmazioneRepository.findById(id);
        return programmazione;
    }

    public List<Programmazione> findAll() {
        return programmazioneRepository.findAll();
    }


    public void deleteProgrammazione(Long id) {
        programmazioneRepository.deleteById(id);
    }

    public List<Programmazione> findAllCurrent() {
        LocalDate firstDate = LocalDate.now();

        return programmazioneRepository.findProgrammazioneCorrente(firstDate);
    }

    public List<Programmazione> findAllStorico() {
        LocalDate date = LocalDate.now();
        return programmazioneRepository.findProgrammazioneStorico(date);
    }

    public List<Programmazione> findAllQuery(String firstData, String secondData) {
        LocalDate first = LocalDate.parse(firstData);
        LocalDate second = LocalDate.parse(secondData);

        return programmazioneRepository.findProgrammazioneQuery(first, second);
    }


}
