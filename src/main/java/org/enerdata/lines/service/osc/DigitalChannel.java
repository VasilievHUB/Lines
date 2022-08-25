package org.enerdata.lines.service.osc;

public class DigitalChannel extends Channel {

	
	private int normalCondition;
	private byte[] values;
	
	public byte[] getValues() {
		return values;
	}
	public void setValues(byte[] values) {
		this.values = values;
	}
	
	public int getNormalCondition() {
		return normalCondition;
	}
	public void setNormalCondition(int normalCondition) {
		this.normalCondition = normalCondition;
	}

	
	
}
