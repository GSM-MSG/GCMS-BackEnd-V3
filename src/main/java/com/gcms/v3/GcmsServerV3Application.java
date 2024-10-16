package com.gcms.v3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GcmsServerV3Application {

	public static void main(String[] args) {
		SpringApplication.run(GcmsServerV3Application.class, args);
	}

}
