package com.Project.CineMille.service;

import com.Project.CineMille.model.Film;
import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public void addFilm(Film film) {
        filmRepository.save(film);
    }

    public void updateFilm(Film film) {
        filmRepository.save(film);
    }

    public Optional<Film> findByID(long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public List<Film> findAllCurrent() {
        LocalDate firstDate = LocalDate.now();
        return filmRepository.findFilmCorrente(firstDate);
    }

    public List<Film> findAllStorico() {
        LocalDate date = LocalDate.now();
        return filmRepository.findFilmStorico(date);
    }

    public List<Film> findAllQuery(String firstData, String secondData) {
        LocalDate first = LocalDate.parse(firstData);
        LocalDate second = LocalDate.parse(secondData);

        return filmRepository.findFilmQuery(first, second);
    }


}
