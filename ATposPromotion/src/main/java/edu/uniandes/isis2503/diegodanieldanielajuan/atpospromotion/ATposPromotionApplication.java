package edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.entity.Promotion;

@EnableDiscoveryClient
@SpringBootApplication
public class ATposPromotionApplication {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, Promotion> redisTemplate() {
		RedisTemplate<String, Promotion> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ATposPromotionApplication.class, args);
	}

}
