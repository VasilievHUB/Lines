package org.enerdata.lines.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.enerdata.lines.domain.Oscillogramm;
import org.enerdata.lines.service.osc.OscillogrammUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/osc")
public class OscillogrammController {

	@PostMapping("/upload")
	@ResponseBody
	public Oscillogramm uploadOsc(@RequestParam("cfg") MultipartFile cfgFile, @RequestParam("dat") MultipartFile datFile) {

		if(!cfgFile.isEmpty()) {
			
			try {
				//Сохраняем файлы в нужную папку
				//TODO Место хранения осциллограмм подтягивать из файла конфигурации
				File cfgStoreFile = new File("C:\\tempJavaStorage\\" + cfgFile.getOriginalFilename());
				File dataStoreFile = new File("C:\\tempJavaStorage\\" + datFile.getOriginalFilename());
				
				byte[] bytes = cfgFile.getBytes();
				BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(cfgStoreFile));
				bo.write(bytes);
				bo.close();
				bytes = datFile.getBytes();
				bo = new BufferedOutputStream(new FileOutputStream(dataStoreFile));
				bo.write(bytes);
				
				bo.close();
				Oscillogramm osc = OscillogrammUtils.getOscFromComtrade(cfgStoreFile, dataStoreFile);
				
				return osc;
				} catch (Exception e) {
					System.out.println(e.toString());
					//return e.toString();
				}
		}
		return null;
		//return null;
		
	}
	
}
