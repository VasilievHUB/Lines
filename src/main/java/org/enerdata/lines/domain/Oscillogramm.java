package org.enerdata.lines.domain;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.enerdata.lines.service.osc.AnalogChannel;
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
	private File pathToCFGFile;
	private File pathToDATFile;
	private int frequency;
	private int nRates;
	private int[] sampleRate;
	private int[] lastSample;
	private String firstSamleTime;
	private String triggerPointTime;
	private String dataFileFormat;
	private int timeMultiplication;
	//Временные метки с учетом временного множителя
	private List<Long> timePoints;
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
		
	public List<Long> getTimePoints() {
		return timePoints;
	}
	public void setTimePoints(List<Long> timePoints) {
		this.timePoints = timePoints;
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

	public File getPathToCFGFile() {
		return pathToCFGFile;
	}
	public void setPathToCFGFile(File pathToCFGFile) {
		this.pathToCFGFile = pathToCFGFile;
	}
	public File getPathToDATFile() {
		return pathToDATFile;
	}
	public void setPathToDATFile(File pathToDATFile) {
		this.pathToDATFile = pathToDATFile;
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
	public String getTriggerPointTime() {
		return triggerPointTime;
	}
	public void setTriggerPointTime(String startTime) {
		this.triggerPointTime = startTime;
	}
	public String getId() {
		return id;
	}
	
	
	public String getDataFileFormat() {
		return dataFileFormat;
	}
	public void setDataFileFormat(String dataFileFormat) {
		this.dataFileFormat = dataFileFormat;
	}
	public int getTimeMultiplication() {
		return timeMultiplication;
	}
	public void setTimeMultiplication(int timeMultiplication) {
		this.timeMultiplication = timeMultiplication;
	}
	public void setValusFromBinDat() {
		int offset=(this.getAnalogChannelQuantity()+(this.getDigitalChannelQuantity()+15)/16)*2+8;
		System.out.println(Integer.toBinaryString(0|(byte)1<<8));
		try(RandomAccessFile raf=new RandomAccessFile(this.pathToDATFile, "r")){
		this.setTimePoints(new ArrayList<>());	
		
		
		//Получить временные метки с учетом временного множителя
		for(int i = 0; i<raf.length()/(offset); i++) {
			raf.seek(i*offset+4);
			long i1=raf.read();
			int i2=raf.read()<<8;
			int i3=raf.read()<<16;
			int i4=raf.read()<<24;
			this.getTimePoints().add((i1|i2|i3|i4)*this.getTimeMultiplication());
			
		}
		
		this.setChannels(new ArrayList<>());
		for(int i = 0; i < this.getAnalogChannelQuantity();i++) {
			this.getChannels().add(new AnalogChannel());
			((AnalogChannel) this.getChannels().get(i)).setValues(new double[(int) (raf.length()/offset)]);
			for(int j = 0; j < raf.length()/offset;j++) {
				raf.seek(offset*j+8+(this.getChannels().size()));
				int i1=raf.read();
				int i2=raf.read()<<8;
				
				((AnalogChannel) this.getChannels().get(i)).getValues()[j]=(i1|i2);
				System.out.println(((AnalogChannel) this.getChannels().get(i)).getValues()[j]);
			}
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}



}
