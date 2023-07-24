package com.Project.CineMille.controller;

import com.Project.CineMille.model.Film;
import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.service.FilmService;
import com.Project.CineMille.service.ProgrammazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programmazione")
public class ProgrammazioneController {


    @Autowired
    private ProgrammazioneService programmazioneService;


    @GetMapping("/all")
    public List<Programmazione> getAllProgrammazione() {
        return programmazioneService.findAll();
    }

    @GetMapping("/all/{query}")
    public List<Programmazione> getAllProgrammazione(@PathVariable("query") String query) {
        if (query.equals("Corrente")) {
            return programmazioneService.findAllCurrent();
        }
        if (query.equals("Storico")) {
            return programmazioneService.findAllStorico();
        }
        return programmazioneService.findAll();
    }

    @GetMapping("/all/{firstData}/{secondData}")
    public List<Programmazione> getAllProgrammazioneQuery(@PathVariable("firstData") String firstData, @PathVariable("secondData") String secondData) {
        return programmazioneService.findAllQuery(firstData, secondData);
    }

    @GetMapping("/find/{id}")
    public Optional<Programmazione> getProgrammazioneById(@PathVariable("id") Long id) {
        return programmazioneService.findByID(id);
    }

    @PostMapping("/add")
    public void addProgrammazione(@RequestBody Programmazione programmazione) {
        programmazioneService.addProgrammazione(programmazione);
    }

    @PutMapping("/update")
    public void updateProgrammazione(@RequestBody Programmazione programmazione) {
        programmazioneService.updateProgrammazione(programmazione);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProgrammazione(@PathVariable("id") Long id) {
        programmazioneService.deleteProgrammazione(id);
    }
}
