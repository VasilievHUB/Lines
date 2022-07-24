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
	public String uploadOsc(@RequestParam("cfg") MultipartFile cfgFile, @RequestParam("dat") MultipartFile datFile) {

		if(!cfgFile.isEmpty()) {
			
			try {
				Oscillogramm osc = OscillogrammUtils.getOscFromComtrade(cfgFile.getInputStream(), null);
				byte[] bytes = cfgFile.getBytes();
				//TODO Место хранения осциллограмм подтягивать из файла конфигурации
				BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(new File("C:\\tempJavaStorage\\" + cfgFile.getOriginalFilename())));
				
				bo.write(bytes);
				bo.close();
				
				} catch (Exception e) {
					return e.toString();
				}
		} else return cfgFile.getOriginalFilename() + "файл пустой";
		return "Загружен файл " + cfgFile.getOriginalFilename();
		
	}
	
}
