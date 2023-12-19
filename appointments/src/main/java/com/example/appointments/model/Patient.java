package com.example.appointments.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Patients")
@SequenceGenerator(name = "Patients_SEQ", sequenceName = "Patients_SEQ", allocationSize = 1)
public class Patient implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Patients_SEQ")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private String name;
	
	@OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

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

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}