package org.ersun.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  // Belirli bir versiyona kadar zorunlu fakat belirli bir versiyondan sonra pom.xml e dependenct ekleyip jar ların Classpath te olması yetiyor.
public class MovieInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

}
