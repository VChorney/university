package com.university.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Department;
import com.university.repository.DepartmentRepository;

@Service
public class DepartmentService implements BaseService<Department> {

	private DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Iterable<Department> saveAll(Iterable<Department> departments) {
		return departmentRepository.saveAll(departments);
	}

	@Override
	public Optional<Department> findById(Long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return departmentRepository.existsById(id);
	}

	@Override
	public Iterable<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Iterable<Department> findAllById(Iterable<Long> departments) {
		return findAllById(departments);
	}

	@Override
	public long count() {
		return departmentRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public void delete(Department department) {
		departmentRepository.delete(department);
	}

	@Override
	public void deleteAll(Iterable<Department> departments) {
		departmentRepository.deleteAll(departments);
	}

	@Override
	public void deleteAll() {
		departmentRepository.deleteAll();
	}
}
