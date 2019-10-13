package com.simple.serviceDeskApplication;

import com.simple.serviceDeskApplication.config.JpaConfig;
import com.simple.serviceDeskApplication.config.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		JpaConfig.class,
		MainConfig.class
})
@Slf4j
@ComponentScan(basePackages = "com.simple.serviceDeskApplication")
@EntityScan(basePackages = "com.simple.serviceDeskApplication")
@SpringBootTest
@AutoConfigureWebClient
public class ServiceDeskApplicationTests {

	@Test
	public void contextLoads() {
	}

}
