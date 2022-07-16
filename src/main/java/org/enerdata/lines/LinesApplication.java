package org.enerdata.lines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "org.enerdata.lines.*")
public class LinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinesApplication.class, args);
	}

}
