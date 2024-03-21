package com.enviro.assessment.gard001.seshagirisainni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.enviro.assessment.gard001.seshagirisainni.entity.EnvironmentalData;

public interface EnvironmentalDataService {
	boolean uploadFile(MultipartFile file);
	List<EnvironmentalData> retrieveData();
	Optional<EnvironmentalData> retrieveData(Long id);
}
