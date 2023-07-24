package com.Project.CineMille.controller;

import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.model.Proiezione;
import com.Project.CineMille.service.ProgrammazioneService;
import com.Project.CineMille.service.ProiezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proiezione")
public class ProiezioneController {

    @Autowired
    private ProiezioneService proiezioneService;

    @GetMapping("/all")
    public List<Proiezione> getAllProiezione() {
        return proiezioneService.findAll();
    }

    @GetMapping("/all/{query}")
    public List<Proiezione> getAllProgrammazione(@PathVariable("query") String query) {
        if (query.equals("Corrente")) {
            return proiezioneService.findAllCurrent();
        }
        if (query.equals("Storico")) {
            return proiezioneService.findAllStorico();
        }
        return proiezioneService.findAll();
    }

    @GetMapping("/all/{firstData}/{secondData}")
    public List<Proiezione> getAllProgrammazioneQuery(@PathVariable("firstData") String firstData, @PathVariable("secondData") String secondData) {
        return proiezioneService.findAllQuery(firstData, secondData);
    }


    @GetMapping("/find/{id}")
    public Optional<Proiezione> getProiezioneById(@PathVariable("id") Long id) {
        return proiezioneService.findByID(id);
    }

    @PostMapping("/add")
    public void addProiezione(@RequestBody Proiezione proiezione) {
        proiezioneService.addProiezione(proiezione);
    }

    @PutMapping("/update")
    public void updateProiezione(@RequestBody Proiezione proiezione) {
        proiezioneService.updateProiezione(proiezione);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProiezione(@PathVariable("id") Long id) {
        proiezioneService.deleteProiezione(id);
    }

}
