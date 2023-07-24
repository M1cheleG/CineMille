package com.Project.CineMille.controller;

import com.Project.CineMille.model.Film;
import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/all")
    public List<Film> getAllFilm() {
        return filmService.findAll();
    }

    @GetMapping("/all/{query}")
    public List<Film> getAllProgrammazione(@PathVariable("query") String query) {
        if (query.equals("Corrente")) {
            return filmService.findAllCurrent();
        }
        if (query.equals("Storico")) {
            return filmService.findAllStorico();
        }
        return filmService.findAll();
    }

    @GetMapping("/all/{firstData}/{secondData}")
    public List<Film> getAllProgrammazioneQuery(@PathVariable("firstData") String firstData, @PathVariable("secondData") String secondData) {
        return filmService.findAllQuery(firstData, secondData);
    }

    @GetMapping("/find/{id}")
    public Optional<Film> getFilmById(@PathVariable("id") Long id) {
        return filmService.findByID(id);
    }

    @PostMapping("/add")
    public void addFilm(@RequestBody Film film) {
        filmService.addFilm(film);
    }

    @PutMapping("/update")
    public void updateFilm(@RequestBody Film film) {
        filmService.updateFilm(film);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilm(id);
    }


}
