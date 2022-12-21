package com.example.springboot.MedApp;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "logentries")
public class Logentries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private Long edittimestamp;
    private int systolic;
    private int diastolic;
    private int pulse;
    private boolean arrhythmias;
    private String comm;
    private Long measuretimestamp;


    @Override
    public String toString() {
        return userid;
    }

    public boolean getArrhythmias() {
        return arrhythmias;
    }

}


