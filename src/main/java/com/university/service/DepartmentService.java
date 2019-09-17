package com.university.service;

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
	public Iterable<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public void delete(Department department) {
		departmentRepository.delete(department);
	}

}
