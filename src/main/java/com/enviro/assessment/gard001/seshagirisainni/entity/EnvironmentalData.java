package com.enviro.assessment.gard001.seshagirisainni.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EnvironmentalData {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    private String location;
    
    private String temperature;
    
    private String humidity;
    
    private String airQuality;

}
