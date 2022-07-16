package org.enerdata.lines.service.line;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LineZone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double distance;
	private double x1;
	private double r1;
	private double x0;
	private double r0;

	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getR1() {
		return r1;
	}
	public void setR1(double r1) {
		this.r1 = r1;
	}
	public double getX0() {
		return x0;
	}
	public void setX0(double x0) {
		this.x0 = x0;
	}
	public double getR0() {
		return r0;
	}
	public void setR0(double r0) {
		this.r0 = r0;
	}
	public int getId() {
		return id;
	}
	
	
}
