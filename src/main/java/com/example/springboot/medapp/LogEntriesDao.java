package com.example.springboot.medapp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogEntriesDao extends JpaRepository<Logentries, Long> {

    //@Query(value = "select l.id, l.USERID, l.EDITTIMESTAMP, l.SYSTOLIC, l.DIASTOLIC, l.PULSE, l.ARRHYTHMIAS,l.COMM, l.MEASURETIMESTAMP from logentries l where l.userid = ?1" , nativeQuery = true)
    Logentries findByUserid(String userid);


    @Query(value = "select l.id, l.USERID, l.EDITTIMESTAMP, l.SYSTOLIC, l.DIASTOLIC, l.PULSE, l.ARRHYTHMIAS,l.COMM, l.MEASURETIMESTAMP from logentries l where l.userid = ?1" , nativeQuery = true)
    List<Logentries> findAllByUserid(String userid);




}
