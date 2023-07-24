package com.Project.CineMille.service;

import com.Project.CineMille.model.Film;
import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.model.Proiezione;
import com.Project.CineMille.repository.ProgrammazioneRepository;
import com.Project.CineMille.repository.ProiezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProiezioneService {
    @Autowired
    ProiezioneRepository proiezioneRepository;

    public void addProiezione(Proiezione proiezione) {
        proiezioneRepository.save(proiezione);
    }

    public void updateProiezione(Proiezione proiezione) {
        proiezioneRepository.save(proiezione);
    }

    public Optional<Proiezione> findByID(long id) {
        Optional<Proiezione> proiezione = proiezioneRepository.findById(id);
        return proiezione;
    }

    public List<Proiezione> findAll() {
        return proiezioneRepository.findAll();
    }

    public void deleteProiezione(Long id) {
        proiezioneRepository.deleteById(id);
    }

    public List<Proiezione> findAllCurrent() {
        LocalDate firstDate = LocalDate.now();
        return proiezioneRepository.findProiezioneCorrente(firstDate);
    }

    public List<Proiezione> findAllStorico() {
        LocalDate date = LocalDate.now();
        return proiezioneRepository.findProiezioneStorico(date);
    }

    public List<Proiezione> findAllQuery(String firstData, String secondData) {
        LocalDate first = LocalDate.parse(firstData);
        LocalDate second = LocalDate.parse(secondData);

        return proiezioneRepository.findProiezioneQuery(first, second);
    }


}
