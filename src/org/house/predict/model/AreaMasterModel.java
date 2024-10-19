package org.house.predict.model;

public class AreaMasterModel extends cityMasterModel {
	private int areaId;
	private String areaName;
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
