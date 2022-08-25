package org.enerdata.lines.service.osc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.enerdata.lines.domain.Oscillogramm;

public class OscillogrammUtils {
	
	public static Oscillogramm getOscFromComtrade(File cfgStoreFile, File dataStoreFile) {
		
		Oscillogramm osc = new Oscillogramm();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cfgStoreFile)))){
			
			String[] tmp;
			//Добавляем расположение файлов осциллограмм
			osc.setPathToCFGFile(cfgStoreFile);
			osc.setPathToDATFile(dataStoreFile);
			//1-я строка .CFG ** Название реле ** идентефикатор реле ** год выпуска (это не точно)
			tmp = br.readLine().split(",");
			osc.setStationName(tmp[0]);
			osc.setStationID(tmp[1]);
			osc.setRevYear(Integer.parseInt(tmp[2]));
			
			//2-я строка .CFG ** Количество каналов ** Аналоговые каналы ** Дискретные каналы
			tmp = br.readLine().split(",");
			osc.setChannelQuantity(Integer.parseInt(tmp[0].trim()));
			int analogQuantity=Integer.parseInt(tmp[1].trim().substring(0, tmp[1].trim().length()-1));
			osc.setAnalogChannelQuantity(analogQuantity);
			int digitalQuantity=Integer.parseInt(tmp[2].trim().substring(0, tmp[2].trim().length()-1));
			osc.setDigitalChannelQuantity(digitalQuantity);
			
			//3-Xxx строки .CFG ** Парсинг каналов
			osc.setChannels(new ArrayList<Channel>());
			for(int i=0;i<analogQuantity;i++) {
				osc.getChannels().add(getAnalogChannelByCFG(br.readLine()));
			}
			
			for(int i=0;i<digitalQuantity;i++) {
				osc.getChannels().add(getDigitalChannelByCFG(br.readLine()));
			}
			
			osc.setFrequency(Integer.parseInt(br.readLine().trim()));
			osc.setnRates(Integer.parseInt(br.readLine().trim()));
			if(osc.getnRates()>1) {			
					osc.setSampleRate(new int[osc.getnRates()]);
				for(int i=0;i<osc.getnRates();i++) {
					tmp=br.readLine().split(",");
					osc.getSampleRate()[i]=Integer.parseInt(tmp[0].trim());
					osc.getLastSample()[i]=Integer.parseInt(tmp[1].trim());
				} 
					
				} else {
					osc.setSampleRate(new int[1]);
					osc.setLastSample(new int[1]);
					tmp=br.readLine().split(",");
					osc.getSampleRate()[0]=Integer.parseInt(tmp[0].trim());
					osc.getLastSample()[0]=Integer.parseInt(tmp[1].trim());
			}
			osc.setFirstSamleTime(br.readLine());
			osc.setTriggerPointTime(br.readLine());
			osc.setDataFileFormat(br.readLine().trim().toUpperCase());
			osc.setTimeMultiplication(Integer.parseInt(br.readLine().trim().substring(0, 1)));
			osc.setValusFromBinDat();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return osc;
	}
		//Возвращает аналоговый канал по перреданной строке из *.CFG
		public static AnalogChannel getAnalogChannelByCFG(String cfgLine) {
			
			AnalogChannel result = new AnalogChannel();
			String[] options=cfgLine.split(",");
			
			// номер канала ** название канала ** фаза ** цепь ** единицы измерения ** a ** b ** skew ** min ** max ** prime ** second ** PS
			result.setNumber(Integer.parseInt(options[0].trim()));
			result.setName(options[1].trim());
			result.setPhase(options[2].trim());
			result.setCircut(options[3].trim());
			result.setUnits(options[4].trim());
			result.setA(Double.parseDouble(options[5].trim()));
			result.setB(Double.parseDouble(options[6].trim()));
			result.setSkew(Double.parseDouble(options[7].trim()));
			result.setMin(Integer.parseInt(options[8].trim()));
			result.setMax(Integer.parseInt(options[9].trim()));
			result.setPrimaryVal(Double.parseDouble(options[10].trim()));
			result.setSecondoryVal(Double.parseDouble(options[11].trim()));
			result.setPrimeSecondVal(options[12].trim());
			
			return result;
			
		}
		
		//Возвращает дискретный канал по перреданной строке из *.CFG
		public static DigitalChannel getDigitalChannelByCFG(String cfgLine) {
			
			DigitalChannel result = new DigitalChannel();
			String[] options=cfgLine.split(",");
			
			// номер канала ** название канала ** ?? ** ?? ** нормальное состояние 
			result.setNumber(Integer.parseInt(options[0].trim()));
			result.setName(options[1].trim());
			result.setNormalCondition(Integer.parseInt(options[4].trim()));
			
			return result;
			
		}
		
		
		//TODO Удалить статический метод, логика перенесена в класс Oscillogramm
//		public static Oscillogramm getValusFromBinDat(Oscillogramm osc, File file) {
//			int offset=osc.getAnalogChannelQuantity()+(osc.getDigitalChannelQuantity()+15)/16;
//			try(RandomAccessFile raf=new RandomAccessFile(file, "r")){
//				
//			for(int i = 0; i<osc.getAnalogChannelQuantity()+osc.getDigitalChannelQuantity(); i++) {
//				raf.seek(i*offset+4);
//				osc.getTimePoints().add(0 | raf.readByte() | (0 | raf.readByte())<<8 | (0 | raf.readByte())<<16 | (0 | raf.readByte())<<24 );
//				System.out.println(osc.getTimePoints().get(i));
//				
//			}	
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//			return osc;
//		}
		
		
		
		
		
}
