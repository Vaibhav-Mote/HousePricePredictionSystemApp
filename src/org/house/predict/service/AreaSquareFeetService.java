package org.house.predict.service;

import org.house.predict.model.AreaSquareModel;
import org.house.predict.repository.AreaSquareFeetRepository;

public class AreaSquareFeetService {
	AreaSquareFeetRepository areaSq = new AreaSquareFeetRepository();

	public boolean isAddAreaSquareFeet(AreaSquareModel model) {
		return areaSq.isAddAreaSquareFeet(model);
	}
	
	public int getSquareFeetID(int sqFeet) {
		return areaSq.getSquareFeetID(sqFeet);
	}

}
