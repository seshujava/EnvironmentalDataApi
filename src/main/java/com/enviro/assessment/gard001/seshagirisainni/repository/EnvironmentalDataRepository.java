package com.enviro.assessment.gard001.seshagirisainni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enviro.assessment.gard001.seshagirisainni.entity.EnvironmentalData;

@Repository
public interface EnvironmentalDataRepository extends JpaRepository<EnvironmentalData, Long>{

}
