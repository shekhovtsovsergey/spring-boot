package com.example.springboot.MedApp;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/logentries")
public class LogentriesController {

    private final LogentriesService logentriesService;


    @GetMapping("/{userid}")
    public ResponseEntity<List<Logentries>> getLogentries(@PathVariable("userid") String userid) {
        List<Logentries> logentries = logentriesService.findAllByUserid(userid);
        return new ResponseEntity<List<Logentries>>(logentries, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> saveLogentries(@RequestBody Logentries logentries) {
        Logentries savedLogentries = logentriesService.save(logentries);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteLogentries(@RequestBody Logentries logentries) {
        Logentries savedLogentries = logentriesService.deleteById(logentries);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);

    }


}
