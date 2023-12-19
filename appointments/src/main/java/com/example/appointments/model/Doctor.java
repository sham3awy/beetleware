package com.example.appointments.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Doctors")
@SequenceGenerator(name = "Doctors_SEQ", sequenceName = "Doctors_SEQ", allocationSize = 1)
public class Doctor implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Doctors_SEQ")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}