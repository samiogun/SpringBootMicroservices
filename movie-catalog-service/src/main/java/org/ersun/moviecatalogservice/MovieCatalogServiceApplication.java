package org.ersun.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient  // Belirli bir versiyona kadar zorunlu fakat belirli bir versiyondan sonra pom.xml e dependenct ekleyip jar ların Classpath te olması yetiyor.
public class MovieCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

    @Bean(value = "resttemplate")
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

}
