package com.irfaan.danspro;

import com.irfaan.danspro.config.ApplicationProperties;
import com.irfaan.danspro.entities.Users;
import com.irfaan.danspro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@EnableConfigurationProperties({ApplicationProperties.class})
@SpringBootApplication
public class DansproApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public DansproApplication(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(DansproApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Users entity = new Users();
		entity.setUsername("ahmadirfaan");
		entity.setPassword(bCryptPasswordEncoder.encode("password"));

		Users entity1 = new Users();
		entity1.setUsername("irfaan");
		entity1.setPassword(bCryptPasswordEncoder.encode("password"));

		Users entity2 = new Users();
		entity2.setUsername("ahmad");
		entity2.setPassword(bCryptPasswordEncoder.encode("password"));

		userRepository.saveAll(Arrays.asList(entity, entity1, entity2));
	}
}
