package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
public class ATposBillApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATposBillApplication.class, args);
	}

}


