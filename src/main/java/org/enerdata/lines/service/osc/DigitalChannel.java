package org.enerdata.lines.service.osc;

public class DigitalChannel extends Channel {

	
	private int normalCondition;
	private short[] values;
	
	public short[] getValues() {
		return values;
	}
	public void setValues(short[] values) {
		this.values = values;
	}
	
	public int getNormalCondition() {
		return normalCondition;
	}
	public void setNormalCondition(int normalCondition) {
		this.normalCondition = normalCondition;
	}

	
	
}
