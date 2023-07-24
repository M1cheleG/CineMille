package com.Project.CineMille.controller;

import com.Project.CineMille.model.Film;
import com.Project.CineMille.model.Sala;
import com.Project.CineMille.service.FilmService;
import com.Project.CineMille.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping("/all")
    public List<Sala> getAllSala() {
        return salaService.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Sala> getSalaById(@PathVariable("id") int id) {
        return salaService.findByID(id);
    }

    @PostMapping("/add")
    public void addSala(@RequestBody Sala sala) {
        salaService.addSala(sala);
    }

    @PutMapping("/update")
    public void updateSala(@RequestBody Sala sala) {
        salaService.updateSala(sala);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSala(@PathVariable("id") int id) {
        salaService.deleteSala(id);
    }


}
