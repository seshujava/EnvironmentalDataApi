package com.enviro.assessment.gard001.seshagirisainni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enviro.assessment.gard001.seshagirisainni.entity.EnvironmentalData;
import com.enviro.assessment.gard001.seshagirisainni.exceptions.NoEnvironmentalDataFoundException;
import com.enviro.assessment.gard001.seshagirisainni.service.EnvironmentalDataService;

@RestController
@RequestMapping("/environmental-data")
public class EnvironmentalDataController {

	@Autowired
	EnvironmentalDataService environmentalDataService;

	@PostMapping(value ="/upload")
	public ResponseEntity<String> uploadFile(@Nullable @RequestParam(value = "file",required = false) MultipartFile file) {
		if(environmentalDataService.uploadFile(file)) {
			return ResponseEntity.ok("Environmental Data file uploaded successfully");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload Environmental Data file");
		}
	}

	@GetMapping("/retrieve")
	public ResponseEntity<List<EnvironmentalData>> retrieveData() {
		List<EnvironmentalData> retrieveData = environmentalDataService.retrieveData();
		if(retrieveData.isEmpty()) {
			throw new NoEnvironmentalDataFoundException("No Environmental Data Found");
		}
		return ResponseEntity.ok(retrieveData);
	}

	@GetMapping("/retrieve/{id}")
	public ResponseEntity<Optional<EnvironmentalData>> retrieveData(@PathVariable Long id) {
		Optional<EnvironmentalData> retrieveData = environmentalDataService.retrieveData(id);
		if(retrieveData.isEmpty()) {
			throw new NoEnvironmentalDataFoundException("No Environmental Data Found on this id: "+id);
		}
		return ResponseEntity.ok(retrieveData);
	}
}

