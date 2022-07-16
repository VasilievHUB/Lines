package org.enerdata.lines.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.enerdata.lines.service.VoltageLevel;
import org.enerdata.lines.service.line.LineType;
import org.enerdata.lines.service.line.LineZone;

@Entity
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="ZONE_ID")
	private List<LineZone> distances;
	private String name;
	private LineType lineType;
	private VoltageLevel voltageLevel;
	@OneToOne
	private Substation substationA;
	@OneToOne
	private Substation substationB;
	private double totalDistance;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LineZone> getDistances() {
		return distances;
	}
	public void setDistances(List<LineZone> distances) {
		this.distances = distances;
	}
	public VoltageLevel getVoltageLevel() {
		return voltageLevel;
	}
	public void setVoltageLevel(VoltageLevel voltageLevel) {
		this.voltageLevel = voltageLevel;
	}
	public Substation getSubstationA() {
		return substationA;
	}
	public void setSubstationA(Substation substationA) {
		this.substationA = substationA;
	}
	public Substation getSubstationB() {
		return substationB;
	}
	public void setSubstationB(Substation substationB) {
		this.substationB = substationB;
	}
	public int getId() {
		return id;
	}
	public double getTotalDistance() {
		return totalDistance;
	}
	public LineType getLineType() {
		return lineType;
	}
	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	
	
}
