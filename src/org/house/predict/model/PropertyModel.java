package org.house.predict.model;

import java.util.List;

public class PropertyModel {
	private int pid;
	private String name;
	private int nbed;
	private int nbath;
	private AreaSquareModel sqmodel;
	private DealModel dealmodel;
    private int cityId;
    private int areaId;
	

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public DealModel getDealmodel() {
		return dealmodel;
	}

	public void setDealmodel(DealModel dealmodel) {
		this.dealmodel = dealmodel;
	}

	private List<AminityModel> list;

	private AreaMasterModel areamodel;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbed() {
		return nbed;
	}

	public void setNbed(int nbed) {
		this.nbed = nbed;
	}

	public int getNbath() {
		return nbath;
	}

	public void setNbath(int nbath) {
		this.nbath = nbath;
	}

	public AreaMasterModel getAreamodel() {
		return areamodel;
	}

	public void setAreamodel(AreaMasterModel areamodel) {
		this.areamodel = areamodel;
	}

	public AreaSquareModel getSqmodel() {
		return sqmodel;
	}

	public void setSqmodel(AreaSquareModel sqmodel) {
		this.sqmodel = sqmodel;
	}

	public List<AminityModel> getList() {
		return list;
	}

	public void setList(List<AminityModel> list) {
		this.list = list;
	}
}
