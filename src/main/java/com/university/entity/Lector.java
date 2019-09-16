package com.university.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.university.enums.Degree;
import com.university.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lector")
public class Lector {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "hibernate_sequence", sequenceName = "id_sequence", allocationSize = 1)
	@Getter
	@Setter
	private Long id;

	@Column(name = "first_name")
	@Getter
	@Setter
	private String firstName;

	@Column(name = "last_name")
	@Getter
	@Setter
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	Degree degree;

	@Column(name = "salary")
	@Getter
	@Setter
	Double salary;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "department_lectors",
			joinColumns = {@JoinColumn(name = "lector_id")},
			inverseJoinColumns = {@JoinColumn(name = "department_id")})
	@Getter
	@Setter
	private Set<Department> department = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private Role role;

	public Lector() {
	}

	@Override
	public String toString() {
		return "Lector{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", degrees=" + degree +
				", department=" + department +
				", role=" + role +
				'}';
	}
}
