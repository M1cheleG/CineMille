package com.Project.CineMille.service;

import com.Project.CineMille.model.Sala;
import com.Project.CineMille.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    SalaRepository salaRepository;

    public void addSala(Sala sala) {
        salaRepository.save(sala);
    }

    public void updateSala(Sala sala) {
        salaRepository.save(sala);
    }

    public Optional<Sala> findByID(int id) {
        Optional<Sala> sala = salaRepository.findById(id);
        return sala;
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public void deleteSala(int id) {
        salaRepository.deleteById(id);
    }
}
