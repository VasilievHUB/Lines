package org.enerdata.lines.service.osc;

public class DigitalChannel implements Channel {

	
	private int id;
	private int number;
	private int normalCondition;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNormalCondition() {
		return normalCondition;
	}
	public void setNormalCondition(int normalCondition) {
		this.normalCondition = normalCondition;
	}
	public int getId() {
		return id;
	}
	
	
}
