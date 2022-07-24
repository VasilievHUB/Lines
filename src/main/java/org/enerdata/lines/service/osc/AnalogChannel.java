package org.enerdata.lines.service.osc;

public class AnalogChannel implements Channel {
	
	private int id;
	private int number;
	private String phase;
	private String circut;
	private String units;
	private double a;
	private double b;
	private double skew;
	private int min;
	private int max;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public int getId() {
		return id;
	}

	
}
