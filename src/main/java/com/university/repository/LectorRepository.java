package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.entity.Degree;
import com.university.entity.Lector;
import com.university.entity.Role;

@Repository
public interface LectorRepository extends CrudRepository<Lector, Long> {

	@Query("SELECT l FROM Lector l join l.departments department WHERE department.name = :departmentName and l.role=:role")
	Lector findByRolesAAndDepartment(@Param("role") Role role, @Param("departmentName") String departmentName);

	@Query("SELECT AVG (l.salary) FROM Lector l join l.departments department WHERE department.name = :departmentName")
	Double averageSalaryForDepartment(@Param("departmentName") String departmentName);

	@Query("SELECT COUNT(l.id) FROM Lector l join l.departments department WHERE department.name = :departmentName and l.role=:role")
	Integer countByRoleAndDepartment(@Param("role") Role role, @Param("departmentName") String departmentName);

	List<Lector> findAllByFirstNameLikeOrLastNameLike(String first, String last);

	@Query("SELECT COUNT(l.id) FROM Lector l join l.departments department WHERE department.name = :departmentName and l.degree=:degree")
	Integer countByDegreeAndDepartmen(@Param("departmentName") String departmentName, @Param("degree") Degree degree);
}
