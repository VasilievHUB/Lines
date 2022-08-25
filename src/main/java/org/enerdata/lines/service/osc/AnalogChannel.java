package org.enerdata.lines.service.osc;

public class AnalogChannel extends Channel {
	
	
	private String phase;
	private String circut;
	private String units;
	private double a;
	private double b;
	private double skew;
	private int min;
	private int max;
	private double primaryVal;
	private double secondoryVal;
	private String primeSecondVal;
	private double[] values;
	
	public double[] getValues() {
		return values;
	}
	public void setValues(double[] values) {
		this.values = values;
	}
	
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getCircut() {
		return circut;
	}
	public void setCircut(String circut) {
		this.circut = circut;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getSkew() {
		return skew;
	}
	public void setSkew(double skew) {
		this.skew = skew;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public double getPrimaryVal() {
		return primaryVal;
	}
	public void setPrimaryVal(double primaryVal) {
		this.primaryVal = primaryVal;
	}
	public double getSecondoryVal() {
		return secondoryVal;
	}
	public void setSecondoryVal(double secondoryVal) {
		this.secondoryVal = secondoryVal;
	}
	public String getPrimeSecondVal() {
		return primeSecondVal;
	}
	public void setPrimeSecondVal(String primeSecondVal) {
		this.primeSecondVal = primeSecondVal;
	}
	


	
}
