package com.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.enums.Degree;
import com.university.enums.Role;
import com.university.repository.DepartmentRepository;
import com.university.repository.LectorRepository;

@Service
public class LectorService implements BaseService<Lector> {

	private LectorRepository lectorRepository;

	private DepartmentRepository departmentRepository;

	@Autowired
	public LectorService(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
		this.lectorRepository = lectorRepository;
		this.departmentRepository = departmentRepository;
	}

	public List<Lector> findAllHeads(String departmentName) {
		Department department = departmentRepository.findByName(departmentName);
		return lectorRepository.findByRolesAAndDepartment(Role.HEAD, department);
	}

	public Integer countOfAssociateProfessor() {
		return lectorRepository.countByDegree(Degree.ASSOCIATE_PROFESSOR);
	}

	public Integer countOfAssistant() {
		return lectorRepository.countByDegree(Degree.ASSISTANT);
	}

	public Integer countOfProfessor() {
		return lectorRepository.countByDegree(Degree.PROFESSOR);
	}

	public Double showAverageSalaryOfDepartment(String departmentName) {
		Department department = departmentRepository.findByName(departmentName);
		return lectorRepository.averageSalaryForDepartment(department.getId());
	}

	public Integer countOfEmployeesInDepartment(String departmentName) {
		Department department = departmentRepository.findByName(departmentName);
		return lectorRepository.countByRoleAndDepartment(Role.EMPLOYEE, department);
	}

	public List<Lector> globalSearch(String template) {
		return lectorRepository.findByFirstNameLikeOrLastNameLikeOrDegreeLikeOrRoleLike(template);
	}

	@Override
	public Lector save(Lector lector) {
		return lectorRepository.save(lector);
	}

	@Override
	public Iterable<Lector> saveAll(Iterable<Lector> lectors) {
		return lectorRepository.saveAll(lectors);
	}

	@Override
	public Optional<Lector> findById(Long id) {
		return lectorRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return lectorRepository.existsById(id);
	}

	@Override
	public Iterable<Lector> findAll() {
		return lectorRepository.findAll();
	}

	@Override
	public Iterable<Lector> findAllById(Iterable<Long> obj) {
		return lectorRepository.findAllById(obj);
	}

	@Override
	public long count() {
		return lectorRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		lectorRepository.deleteById(id);
	}

	@Override
	public void delete(Lector lector) {
		lectorRepository.delete(lector);
	}

	@Override
	public void deleteAll(Iterable<Lector> lectors) {
		lectorRepository.deleteAll(lectors);
	}

	@Override
	public void deleteAll() {
		lectorRepository.deleteAll();
	}
}
