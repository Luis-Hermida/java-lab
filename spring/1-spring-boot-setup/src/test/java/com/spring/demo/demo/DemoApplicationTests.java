package com.spring.demo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Smoke test — loads the full Spring context (all beans, config, controllers) without calling HTTP endpoints.
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
