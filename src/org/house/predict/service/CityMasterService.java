package org.house.predict.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.cityMasterModel;
import org.house.predict.model.cityWiseAreaCount;
import org.house.predict.repository.cityMasterRepository;

public class CityMasterService {
	cityMasterRepository cityRepo = new cityMasterRepository();

	public boolean isAddCity(cityMasterModel model) {
		boolean b = cityRepo.isAddNewCity(model);
		return b;
	}

	public List<cityMasterModel> getAllCities() {
//		List<cityMasterModel>list=cityRepo.getAllCities();
//		return list;	
		return cityRepo.getAllCities();
	}

	public boolean isBulkAddCities() {
		return cityRepo.isBulkAddCities();
	}

	public int getcityid(String name) {
		return cityRepo.getcity(name);

	}
	public int getAreaIdAtomatic() {
		return cityRepo.getAreaIdAtomatic();
	}
	public boolean isAddArea(AreaMasterModel model) {
		return cityRepo.isAddArea(model);
	}
	public List<cityWiseAreaCount> cityWiseAreaCount() {
		return cityRepo.cityWiseAreaCount();
	}
	
	public LinkedHashMap getCityWiseAreaName() {
		return cityRepo.getCityWiseAreaName();
	}
	
	public boolean isDeleteCityByName(String cityname) {
		return cityRepo.isDeleteCityByName(cityname);
	}
	
	public boolean isDeleteAreaInCity(String areaname,int cityId) {
		return cityRepo.isDeleteAreaInCity(areaname,cityId);
	}
	
	public boolean isUpdateCity(String oldCityname,String newCityname) {
		return cityRepo.isUpdateCity(oldCityname, newCityname);
	}
	public int getAreaId(AreaMasterModel model) {
		return cityRepo.getAreaId(model);
	}
	public List<String> getAllAreasCityWise(String cityName){
		return cityRepo.getAllAreasCityWise(cityName);
	}
	
}
