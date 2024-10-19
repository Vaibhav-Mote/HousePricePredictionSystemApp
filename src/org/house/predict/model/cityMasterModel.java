package org.house.predict.model;

public class cityMasterModel {
	private int cityid;
	private String cityname;

	public cityMasterModel() {

	}

	public cityMasterModel(int cityid, String cityname) {
		this.cityid = cityid;
		this.cityname = cityname;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

}
