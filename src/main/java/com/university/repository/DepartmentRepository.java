package com.university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

	Department findByName(String name);
}
