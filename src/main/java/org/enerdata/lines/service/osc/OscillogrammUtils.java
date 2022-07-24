package org.enerdata.lines.service.osc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.enerdata.lines.domain.Oscillogramm;

public class OscillogrammUtils {
	
	public static Oscillogramm getOscFromComtrade(InputStream cfgFile, File datFile) {
		
		Oscillogramm osc = new Oscillogramm();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(cfgFile))){
			String[] tmp;
			//1-я строка .CFG ** Название реле ** идентефикатор реле ** год выпуска (это не точно)
			tmp = br.readLine().split(",");
			osc.setStationName(tmp[0]);
			osc.setStationID(tmp[1]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return osc;
	}

}
