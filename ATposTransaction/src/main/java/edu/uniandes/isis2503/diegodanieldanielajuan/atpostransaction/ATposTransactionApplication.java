package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
public class ATposTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATposTransactionApplication.class, args);
	}

}