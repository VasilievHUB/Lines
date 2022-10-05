package org.enerdata.lines.domain;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.enerdata.lines.service.osc.AnalogChannel;
import org.enerdata.lines.service.osc.Channel;
import org.enerdata.lines.service.osc.DigitalChannel;

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
	public void setValuesFromBinDat() {
		int offset=(this.getAnalogChannelQuantity()+(this.getDigitalChannelQuantity()+15)/16)*2+8;
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
		//Получить аналоговые каналы (реальные значения)
		for(int i = 0; i < this.getAnalogChannelQuantity();i++) {
			
			AnalogChannel tmpChannel = (AnalogChannel) this.getChannels().get(i);
			tmpChannel.setValues(new double[(int) (raf.length()/offset)]);
			
			for(int j = 0; j < raf.length()/offset;j++) {
				raf.seek(offset*j+8+i*2);
				int i1=raf.read();
				int i2=raf.read()<<8;
				short tmpValue = (short) (0|i1|i2);				
				tmpChannel.getValues()[j]=(tmpValue*tmpChannel.getA()+tmpChannel.getB());
			}
			this.getChannels().set(i, tmpChannel);
		}
		
		//Получить дискретные каналы
		for(int i = 0; i < this.getDigitalChannelQuantity();i++) {
			
			DigitalChannel tmpChannel = (DigitalChannel) this.getChannels().get(this.getAnalogChannelQuantity()+i);
			tmpChannel.setValues(new short[(int) (raf.length()/offset)]);
			
			for(int j = 0; j < raf.length()/offset;j++) {
				int currentByte=i/8;
				raf.seek(offset*j+8+this.getAnalogChannelQuantity()*2+currentByte);
				byte i1=(byte) ((byte) ((raf.read()>>(i-currentByte*8))&1));
				tmpChannel.getValues()[j]=(short) i1;
			}
			this.getChannels().set(i+this.getAnalogChannelQuantity(), tmpChannel);
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}



}
