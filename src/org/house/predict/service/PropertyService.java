package org.house.predict.service;

import java.util.List;

import org.house.predict.model.PropertyModel;
import org.house.predict.repository.PropertyRepository;

public class PropertyService {
	PropertyRepository propRepo = new PropertyRepository();

	public boolean isAddNewProperty(PropertyModel model) {
		return propRepo.isAddNewProperty(model);

	}
	
	public List<Object[]>getAreaWisePropertyCount(String cityname)
	{
		return propRepo.getAreaWisePropertyCount(cityname);
	}

}
