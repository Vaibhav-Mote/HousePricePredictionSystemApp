package org.house.predict.model;
public class AreaSquareModel {
	private int id;
	private int squateFeet;
	public int getId() {
		return id;
	}
	
	public AreaSquareModel() {
		
	}
	
	public AreaSquareModel(int id, int squateFeet) {
		super();
		this.id = id;
		this.squateFeet = squateFeet;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSquateFeet() {
		return squateFeet;
	}
	public void setSquateFeet(int squateFeet) {
		this.squateFeet = squateFeet;
	}

}
