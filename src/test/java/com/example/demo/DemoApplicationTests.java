package com.example.demo;

import com.example.demo.pojo.Dog;
import com.example.demo.pojo.Person;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	Dog dog;

	@Autowired
	Person person;

	@Autowired
	User user;

	@Test
	void contextLoads() {

		System.out.println(user);
	}

}
