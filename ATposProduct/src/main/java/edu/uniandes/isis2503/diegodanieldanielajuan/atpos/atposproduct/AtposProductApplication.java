package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AtposProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtposProductApplication.class, args);
	}

}
