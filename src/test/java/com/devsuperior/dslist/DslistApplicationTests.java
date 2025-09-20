package com.devsuperior.dslist;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Testcontainers
@SpringBootTest
class DslistApplicationTests {

	@JavaDispatcher.Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14")
			.withDatabaseName("dslist")
			.withUsername("postgres")
			.withPassword("07111519");

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@Test
	void contextLoads() {
	}
}
