package com.university.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.university.service.DepartmentService;
import com.university.service.LectorService;

@SpringBootApplication
@ComponentScan({"com.university.service"})
@EntityScan("com.university.entity")
public class Application implements CommandLineRunner {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private LectorService lectorService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	private String inputRequest() {
		try(InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void represent(String request) {
		System.out.print("Who is head of department: ");
		String departmentName = inputRequest();
		System.out.println("Head of {" + departmentName + "} department is {"++"}", departmentName, lectorService.findAllHeads(departmentName).toString());
	}
}
