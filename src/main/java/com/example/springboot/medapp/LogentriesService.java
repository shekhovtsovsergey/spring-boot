package com.example.springboot.medapp;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogentriesService {

    private final LogEntriesDao logEntriesDao;


    @Transactional(readOnly = true)
    public List<Logentries> findAllByUserid(String userid) {
        return logEntriesDao.findAllByUserid(userid);

    }


    public Logentries save(Logentries logentries) {
        if (logentries.getId() != null) {
            Optional<Logentries> logentriesFromDbOptional = logEntriesDao.findById(logentries.getId());
            if (logentriesFromDbOptional.isPresent()) {
                Logentries logentriesFromDb = logentriesFromDbOptional.get();
                logentriesFromDb.setUserid(logentries.getUserid());
                logentriesFromDb.setEdittimestamp(logentries.getEdittimestamp());
                logentriesFromDb.setSystolic(logentries.getSystolic());
                logentriesFromDb.setDiastolic(logentries.getDiastolic());
                logentriesFromDb.setPulse(logentries.getPulse());
                logentriesFromDb.setArrhythmias(logentries.getArrhythmias());
                logentriesFromDb.setComm(logentries.getComm());
                logentriesFromDb.setMeasuretimestamp(logentries.getMeasuretimestamp());
                return logEntriesDao.save(logentriesFromDb);
            }
        }
        return logEntriesDao.save(logentries);
    }


    public Logentries deleteById(Logentries logentries) {
        if (logentries.getId() != null) {
            Optional<Logentries> logentriesFromDbOptional = logEntriesDao.findById(logentries.getId());
            if (logentriesFromDbOptional.isPresent()) {
                logEntriesDao.deleteById(logentries.getId());
                return logentries;
            } else {
                log.error("There isn't log with id {}", logentries.getId());
                return null;
            }
        }
        return logentries;
    }


    public List<Logentries> findAll() {
            return logEntriesDao.findAll();
        }
}

