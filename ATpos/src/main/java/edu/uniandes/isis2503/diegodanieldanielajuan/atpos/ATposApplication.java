package edu.uniandes.isis2503.diegodanieldanielajuan.atpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
	})
@EnableCaching
@EnableMongoRepositories
@ComponentScan(basePackages = "edu.uniandes.isis2503.diegodanieldanielajuan.atpos")
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties")
})
public class ATposApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATposApplication.class, args);
	}

}
