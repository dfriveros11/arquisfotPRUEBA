package edu.uniandes.isis2503.diegodanieldanielajuan.atposproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class AtposProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtposProductApplication.class, args);
	}

}
