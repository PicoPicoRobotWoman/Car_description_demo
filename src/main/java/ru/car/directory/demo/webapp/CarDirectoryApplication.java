package ru.car.directory.demo.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("ru.car.directory.demo")
@EnableTransactionManagement
@ComponentScan("ru.car.directory.demo")
public class CarDirectoryApplication {

	public static void main(String[] args) {
        SpringApplication.run(CarDirectoryApplication.class, args);
	}

}
