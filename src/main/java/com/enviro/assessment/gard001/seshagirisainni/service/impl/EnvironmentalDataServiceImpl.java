package com.enviro.assessment.gard001.seshagirisainni.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.enviro.assessment.gard001.seshagirisainni.entity.EnvironmentalData;
import com.enviro.assessment.gard001.seshagirisainni.repository.EnvironmentalDataRepository;
import com.enviro.assessment.gard001.seshagirisainni.service.EnvironmentalDataService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnvironmentalDataServiceImpl implements EnvironmentalDataService{

	@Autowired
	EnvironmentalDataRepository environmentalDataRepository;

	public boolean uploadFile(MultipartFile file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;

			LocalDate date = null;
			String location = null;
			String temperature = null;
			String humidity = null;
			String airQuality=null;

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Date:")) {
					String dateString = line.split(":")[1].trim();
					date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				} else if (line.startsWith("Location:")) {
					location = line.split(":")[1].trim();
				} else if (line.startsWith("Temperature:")) {
					temperature = line.split(":")[1].trim();					
				} else if (line.startsWith("Humidity:")) {
					humidity = line.split(":")[1].trim();					
				} else if (line.startsWith("Air Quality:")) {
					airQuality = line.split(":")[1].trim();
					// Create and save EnvironmentalData object when all data is available
					if (date != null && location != null && temperature != null && humidity != null) {
						EnvironmentalData environmentalData = new EnvironmentalData();
						environmentalData.setDate(date);
						environmentalData.setLocation(location);
						environmentalData.setTemperature(temperature);
						environmentalData.setHumidity(humidity);
						environmentalData.setAirQuality(airQuality);
						environmentalDataRepository.save(environmentalData);
						log.info("Environmental Data File uploaded successfully");
						// Reset variables for next set of data
						date = null;
						location = null;
						temperature = null;
						humidity = null;
						airQuality=null;
					}
				}
			}
			reader.close();
			return true;
		} catch (Exception e) {
			log.info("Failed to upload Environmental Data file: {}", e.getMessage());
			return false;
		}
	}

	public List<EnvironmentalData> retrieveData() {
		log.info("Retrieving environmental data from the repository");
		List<EnvironmentalData> dataList = environmentalDataRepository.findAll();
        log.info("Retrieved {} environmental data records", dataList.size());
        return dataList;
	}

	public Optional<EnvironmentalData> retrieveData(Long id) {
		log.info("Retrieving environmental data from the repository");
		Optional<EnvironmentalData> envData = environmentalDataRepository.findById(id);
		log.info("Retrieved environmental data record");
		return envData;
	}
}
