package com.university.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.entity.Role;
import com.university.repository.DepartmentRepository;
import com.university.repository.LectorRepository;

public class LectorServiceTest {

	@Mock
	LectorRepository lectorRepository;

	@Mock
	DepartmentRepository departmentRepository;

	@Spy
	Lector lector = new Lector();

	@Spy
	Department department = new Department();

	@InjectMocks
	LectorService lectorService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findHeadOfDepartment() {
		lector = new Lector();
		lector.setId(2L);
		lector.setFirstName("Vasyl");
		lector.setLastName("Chorney");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		department.setName("Prog");
		lector.setDepartments(Collections.singleton(department));
		Mockito.when(lectorRepository.findByRolesAAndDepartment(Role.HEAD, "Prog")).thenReturn(lector);
		assertEquals(lectorService.findHeadOfDepartment("Prog"), lector);
	}

	@Test
	public void countByDegreeInDepartment() {
		Mockito.when(lectorRepository.countByDegreeAndDepartmen("Prog", Degree.PROFESSOR)).thenReturn(Integer.valueOf(1));
		assertEquals(lectorService.countByDegreeInDepartment("Prog", Degree.PROFESSOR), Integer.valueOf(1));
		Mockito.when(lectorRepository.countByDegreeAndDepartmen("Prog", Degree.ASSOCIATE_PROFESSOR)).thenReturn(Integer.valueOf(2));
		assertEquals(lectorService.countByDegreeInDepartment("Prog", Degree.ASSOCIATE_PROFESSOR), Integer.valueOf(2));
		Mockito.when(lectorRepository.countByDegreeAndDepartmen("Prog", Degree.ASSISTANT)).thenReturn(Integer.valueOf(3));
		assertEquals(lectorService.countByDegreeInDepartment("Prog", Degree.ASSISTANT), Integer.valueOf(3));
	}

	@Test
	public void showAverageSalaryOfDepartment() {
		Mockito.when(lectorRepository.averageSalaryForDepartment("Prog")).thenReturn(Double.valueOf(200));
		assertEquals(lectorService.showAverageSalaryOfDepartment("Prog"), Double.valueOf(200));
	}

	@Test
	public void countOfEmployeesInDepartment() {
		Mockito.when(lectorRepository.countByRoleAndDepartment(Role.EMPLOYEE,"Prog")).thenReturn(Integer.valueOf(2));
		assertEquals(lectorService.countOfEmployeesInDepartment("Prog"),Integer.valueOf(2));
	}

	@Test
	public void globalSearch() {
		List<Lector> lectors = new ArrayList<>();
		lector = new Lector();
		lector.setId(2L);
		lector.setFirstName("Vasyl");
		lector.setLastName("Chorney");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		department.setName("Prog");
		lector.setDepartments(Collections.singleton(department));
		lectors.add(lector);
		lector = new Lector();
		lector.setId(3L);
		lector.setFirstName("Vasyl1");
		lector.setLastName("Chorney1");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		lector.setDepartments(Collections.singleton(department));
		lectors.add(lector);
		Mockito.when(lectorRepository.findAllByFirstNameLikeOrLastNameLike("Vas","Vas")).thenReturn(lectors);
		assertEquals(lectorService.globalSearch("Vas"),lectors);
	}

	@Test
	public void save() {
		lector = new Lector();
		lector.setId(2L);
		lector.setFirstName("Vasyl");
		lector.setLastName("Chorney");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		department.setName("Prog");
		lector.setDepartments(Collections.singleton(department));
		Mockito.when(lectorRepository.save(lector)).thenReturn(lector);
		assertEquals(lectorService.save(lector),lector);
	}

	@Test
	public void findAll() {
		List<Lector> lectors = new ArrayList<>();
		lector = new Lector();
		lector.setId(2L);
		lector.setFirstName("Vasyl");
		lector.setLastName("Chorney");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		department.setName("Prog");
		lector.setDepartments(Collections.singleton(department));
		lectors.add(lector);
		lector = new Lector();
		lector.setId(3L);
		lector.setFirstName("Vasyl1");
		lector.setLastName("Chorney1");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		lector.setDepartments(Collections.singleton(department));
		lectors.add(lector);
		Mockito.when(lectorRepository.findAll()).thenReturn(lectors);
		assertEquals(lectorService.findAll(),lectors);
	}

	@Test
	public void delete() {
		lector = new Lector();
		lector.setId(2L);
		lector.setFirstName("Vasyl");
		lector.setLastName("Chorney");
		lector.setRole(Role.HEAD);
		lector.setDegree(Degree.PROFESSOR);
		lector.setSalary(5000.0);
		department.setName("Prog");
		lector.setDepartments(Collections.singleton(department));
		lectorService.delete(lector);
		Mockito.verify(lectorRepository,Mockito.times(1)).delete(lector);
	}
}