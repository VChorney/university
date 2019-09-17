package com.university.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.university.entity.Degree;
import com.university.entity.Lector;
import com.university.service.LectorService;

@SpringBootApplication
@ComponentScan("com.university.service")
@EntityScan("com.university.entity")
@EnableJpaRepositories("com.university.repository")
public class Application implements CommandLineRunner {

	@Autowired
	private LectorService lectorService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try(InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {

			System.out.println("Do you want to start? (Enter 'y' for yes, or something else for no) ");
			String question = inputRequest(inputStreamReader);
			while(Objects.equals(question, "y")) {
				System.out.println("Choose request from 1 to 5");
				String request = inputRequest(inputStreamReader);
				switch(Objects.requireNonNull(request)) {
					case "1":
						whoIsHead(inputStreamReader);
						System.out.println("Continue?");
						question = inputRequest(inputStreamReader);
						break;
					case "2":
						statistic(inputStreamReader);
						System.out.println("Continue?");
						question = inputRequest(inputStreamReader);
						break;
					case "3":
						averageSalary(inputStreamReader);
						System.out.println("Continue?");
						question = inputRequest(inputStreamReader);
						break;
					case "4":
						countOfEmployees(inputStreamReader);
						System.out.println("Continue?");
						question = inputRequest(inputStreamReader);
						break;
					case "5":
						globalSearch(inputStreamReader);
						System.out.println("Continue?");
						question = inputRequest(inputStreamReader);
						break;
					default:
						System.out.println("Bad request");
						System.out.println("Continue?");
						question = inputRequest(inputStreamReader);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private String inputRequest(InputStreamReader inputStreamReader) {
		try{BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void whoIsHead(InputStreamReader inputStreamReader) {
		System.out.print("Who is head of department: ");
		String departmentName = inputRequest(inputStreamReader);
		Lector lector = lectorService.findHeadOfDepartment(departmentName);
		System.out.println("Head of " + departmentName + " department is " + lector.getFirstName() + " " + lector.getLastName());
	}

	private void statistic(InputStreamReader inputStreamReader) {
		System.out.print("Show ");
		String departmentName = inputRequest(inputStreamReader);
		System.out.println(" statistic: ");
		Integer assistants = lectorService.countByDegreeInDepartment(departmentName, Degree.ASSISTANT);
		Integer associateProfessor = lectorService.countByDegreeInDepartment(departmentName, Degree.ASSOCIATE_PROFESSOR);
		Integer professor = lectorService.countByDegreeInDepartment(departmentName, Degree.PROFESSOR);
		System.out.println("Assistatns: " + assistants);

		System.out.println("Associate professors: " + associateProfessor);
		System.out.println("Professors: " + professor);
	}

	private void averageSalary(InputStreamReader inputStreamReader) {
		System.out.print("Show the average salary for department: ");
		String departmentName = inputRequest(inputStreamReader);
		System.out.println("The average salary of " + departmentName + " is " + lectorService.showAverageSalaryOfDepartment(departmentName));
	}

	private void countOfEmployees(InputStreamReader inputStreamReader) {
		System.out.print("Show count of employee for: ");
		String departmentName = inputRequest(inputStreamReader);
		System.out.println(lectorService.countOfEmployeesInDepartment(departmentName));
	}

	private void globalSearch(InputStreamReader inputStreamReader) {
		System.out.print(" Global search by: ");
		String template = inputRequest(inputStreamReader);
		List<Lector> result = lectorService.globalSearch(template);
		for(Lector lector : result) {
			System.out.println(lector.getFirstName() + " " + lector.getLastName() + ",");
		}
	}
}
