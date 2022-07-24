package org.enerdata.lines.domain;

import java.nio.file.Path;
import java.util.List;

import org.enerdata.lines.service.osc.Channel;

public class Oscillogramm {
	
	private String id;
	private String stationName;
	private String stationID;
	private int revYear;
	private int channelQuantity;
	private int analogChannelQuantity;
	private int digitalChannelQuantity;
	private List<Channel> channels;
	private Path pathToOscFiles;
	private int frequency;
	private int nRates;
	private int[] sampleRate;
	private int[] lastSample;
	private String firstSamleTime;
	private String startTime;
	
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	
	
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public int getRevYear() {
		return revYear;
	}
	public void setRevYear(int revYear) {
		this.revYear = revYear;
	}
	public int getChannelQuantity() {
		return channelQuantity;
	}
	public void setChannelQuantity(int channelQuantity) {
		this.channelQuantity = channelQuantity;
	}
	public int getAnalogChannelQuantity() {
		return analogChannelQuantity;
	}
	public void setAnalogChannelQuantity(int analogChannelQuantity) {
		this.analogChannelQuantity = analogChannelQuantity;
	}
	public int getDigitalChannelQuantity() {
		return digitalChannelQuantity;
	}
	public void setDigitalChannelQuantity(int digitalChannelQuantity) {
		this.digitalChannelQuantity = digitalChannelQuantity;
	}
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	public Path getPathToOscFiles() {
		return pathToOscFiles;
	}
	public void setPathToOscFiles(Path pathToOscFiles) {
		this.pathToOscFiles = pathToOscFiles;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getnRates() {
		return nRates;
	}
	public void setnRates(int nRates) {
		this.nRates = nRates;
	}
	public int[] getSampleRate() {
		return sampleRate;
	}
	public void setSampleRate(int[] sampleRate) {
		this.sampleRate = sampleRate;
	}
	public int[] getLastSample() {
		return lastSample;
	}
	public void setLastSample(int[] lastSample) {
		this.lastSample = lastSample;
	}
	public String getFirstSamleTime() {
		return firstSamleTime;
	}
	public void setFirstSamleTime(String firstSamleTime) {
		this.firstSamleTime = firstSamleTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getId() {
		return id;
	}
	
	



}
