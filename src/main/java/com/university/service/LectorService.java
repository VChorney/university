package com.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Degree;
import com.university.entity.Lector;
import com.university.entity.Role;
import com.university.repository.LectorRepository;

@Service
public class LectorService implements BaseService<Lector> {

	private LectorRepository lectorRepository;

	@Autowired
	public LectorService(LectorRepository lectorRepository) {
		this.lectorRepository = lectorRepository;
	}

	public Lector findHeadOfDepartment(String departmentName) {
		return lectorRepository.findByRolesAAndDepartment(Role.HEAD, departmentName);
	}

	public Integer countByDegreeInDepartment(String departmentName, Degree degree) {
		return lectorRepository.countByDegreeAndDepartmen(departmentName,degree);
	}

	public Double showAverageSalaryOfDepartment(String departmentName) {
		return lectorRepository.averageSalaryForDepartment(departmentName);
	}

	public Integer countOfEmployeesInDepartment(String departmentName) {
		return lectorRepository.countByRoleAndDepartment(Role.EMPLOYEE, departmentName);
	}

	public List<Lector> globalSearch(String template) {
		return lectorRepository.findAllByFirstNameLikeOrLastNameLike(template,template);
	}

	@Override
	public Lector save(Lector lector) {
		return lectorRepository.save(lector);
	}

	@Override
	public Iterable<Lector> findAll() {
		return lectorRepository.findAll();
	}

	@Override
	public void delete(Lector lector) {
		lectorRepository.delete(lector);
	}

}
