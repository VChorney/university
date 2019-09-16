package com.university.repository;

import java.util.List;

import javax.swing.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.enums.Degree;
import com.university.enums.Role;

@Repository
public interface LectorRepository extends CrudRepository<Lector, Long> {

	List<Lector> findByRolesAAndDepartment(Role role, Department department);

	Integer countByDegree(Degree degree);

	@Query("SELECT AVG (s.salary) FROM Sur s WHERE (department_id = departmentId)")
	Double averageSalaryForDepartment(@Param("departmentId") Long departmentId);

	Integer countByRoleAndDepartment(Role role, Department department);

	List<Lector> findByFirstNameLikeOrLastNameLikeOrDegreeLikeOrRoleLike(String template);
}
