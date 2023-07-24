package com.Project.CineMille.controller;

import com.Project.CineMille.CineMilleApplication;
import com.Project.CineMille.model.Film;
import com.Project.CineMille.model.Programmazione;
import com.Project.CineMille.model.Proiezione;
import com.Project.CineMille.model.Sala;
import com.Project.CineMille.service.FilmService;
import com.Project.CineMille.service.ProgrammazioneService;
import com.Project.CineMille.service.ProiezioneService;
import com.Project.CineMille.service.SalaService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ImportExcelController {
    @Autowired
    ProgrammazioneService programmazioneService;
    @Autowired
    FilmService filmService;
    @Autowired
    ProiezioneService proiezioneService;
    @Autowired
    SalaService salaService;

    private static final Logger logger = Logger.getLogger(CineMilleApplication.class.getName());


    /*Per mancanza di tempo non riesco a gestire al meglio l'import Excel dei file che caricheranno
     i dati direttamente nel DB facendo tutti i controlli necessari */
    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public void importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;

        //Caricamento Programmazione

        importFilm(files);

        importProgrammazione(files);

        importProiezione(files);

        importSala(files);


    }

    private void importSala(MultipartFile files) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            Sala sala = new Sala();
            XSSFRow row = worksheet.getRow(index);

            if (!salaService.findByID((int) row.getCell(1).getNumericCellValue()).isPresent()) {
                sala.setId((int) row.getCell(1).getNumericCellValue());
            } else {
                logger.warning("sala id non disponibile");
                continue;
            }
            if ((int) row.getCell(2).getNumericCellValue() > 0) {
                sala.setPosti((int) row.getCell(2).getNumericCellValue());
            } else {
                logger.warning("posti errati");
                continue;
            }
            sala.setImax(row.getCell(3).getBooleanCellValue());
            salaService.addSala(sala);

        }

    }

    private void importProiezione(MultipartFile files) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            Proiezione proiezione = new Proiezione();
            XSSFRow row = worksheet.getRow(index);

            if (!proiezioneService.findByID((long) row.getCell(1).getNumericCellValue()).isPresent()) {
                proiezione.setId((long) row.getCell(1).getNumericCellValue());
            } else {
                logger.warning("id uguale, ignorato");
                continue;
            }
            if (programmazioneService.findByID((long) row.getCell(2).getNumericCellValue()).isPresent()) {
                proiezione.setProgrammazione(programmazioneService.findByID((long) row.getCell(2).getNumericCellValue()).get());
            } else {
                logger.warning("programmazione non trovata");
                continue;
            }
            Programmazione programmazione = proiezione.getProgrammazione();
            LocalDate date = row.getCell(3).getLocalDateTimeCellValue().toLocalDate();
            if (programmazione.getDateStart().isBefore(date) && programmazione.getDateEnd().isAfter(date)) {
                proiezione.setDate(row.getCell(3).getLocalDateTimeCellValue());
            } else {
                logger.warning("data non valida");
                continue;
            }
            if (salaService.findByID((int) row.getCell(4).getNumericCellValue()).isPresent()) {
                proiezione.setSala(salaService.findByID((int) row.getCell(2).getNumericCellValue()).get());
            } else {
                logger.warning("programmazione non trovata");
                continue;
            }

            proiezioneService.addProiezione(proiezione);

        }
    }

    private void importFilm(MultipartFile files) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            Film film = new Film();
            XSSFRow row = worksheet.getRow(index);

            if (!filmService.findByID((long) row.getCell(1).getNumericCellValue()).isPresent()) {
                film.setId((long) row.getCell(1).getNumericCellValue());
            } else {
                logger.warning("id uguale, ignorato");
                continue;
            }

            if (row.getCell(2).getNumericCellValue() > 0) {
                film.setDurata((int) row.getCell(2).getNumericCellValue());
            } else {
                logger.warning("id uguale, ignorato");
                continue;
            }
            film.setNome(row.getCell(3).getStringCellValue());
            film.setGenere(row.getCell(4).getStringCellValue());

            filmService.addFilm(film);
        }
    }

    private void importProgrammazione(MultipartFile files) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            Programmazione programmazione = new Programmazione();
            XSSFRow row = worksheet.getRow(index);
            if (!programmazioneService.findByID((long) row.getCell(1).getNumericCellValue()).isPresent()) {
                programmazione.setId((long) row.getCell(1).getNumericCellValue());
            } else {
                logger.warning("id uguale, ignorato");
                continue;
            }
            long differenza = ChronoUnit.DAYS.between(row.getCell(2).getLocalDateTimeCellValue().toLocalDate(), row.getCell(3).getLocalDateTimeCellValue().toLocalDate());

            if (differenza == 7 || differenza == 14 || differenza == 21) {
                programmazione.setDateStart(row.getCell(2).getLocalDateTimeCellValue().toLocalDate());
                programmazione.setDateEnd(row.getCell(3).getLocalDateTimeCellValue().toLocalDate());
            } else {
                logger.warning("date non valide //date non alla distanza giusta");
                continue;
            }

            if (filmService.findByID((long) row.getCell(4).getNumericCellValue()).isPresent()) {
                programmazione.setFilm(filmService.findByID((long) row.getCell(4).getNumericCellValue()).get());
            } else {
                logger.warning("film non valido o film non esistente");
            }

            programmazioneService.addProgrammazione(programmazione);
        }
    }


}
