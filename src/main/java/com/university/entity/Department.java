package com.university.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "hibernate_sequence", sequenceName = "id_sequence", allocationSize = 1)
	@Getter
	@Setter
	private Long id;

	@Column(name = "department_name",unique = true)
	@Getter
	@Setter
	private String name;
}
