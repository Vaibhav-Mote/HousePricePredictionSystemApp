package org.house.predict.service;

import org.house.predict.model.AminityModel;
import org.house.predict.repository.AminityRepository;

public class AminityService {
	AminityRepository aminityRepo=new AminityRepository();
	public boolean isAddAminity(AminityModel model) {
		return aminityRepo.isAddAminity(model);

	}
	public int getAminityId(String aminityName) {
		return aminityRepo.getAminityId(aminityName);
		
	}

}
