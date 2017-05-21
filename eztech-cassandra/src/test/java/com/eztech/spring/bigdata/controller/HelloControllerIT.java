package com.eztech.spring.bigdata.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {


	@Autowired
	private TestRestTemplate template;

 /**
  * 
  */
	@Test
	public void getHello() throws Exception {
		String response = template.getForObject("/", String.class);
		assertThat(response, equalTo("Hello, spring big data!"));
	}
}

