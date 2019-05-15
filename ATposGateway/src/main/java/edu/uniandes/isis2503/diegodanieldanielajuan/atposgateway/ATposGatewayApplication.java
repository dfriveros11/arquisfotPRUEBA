package edu.uniandes.isis2503.diegodanieldanielajuan.atposgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ATposGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATposGatewayApplication.class, args);
	}

}
