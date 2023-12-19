package com.example.appointments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointments.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	// Custom query to find doctor by name
	List<Doctor> findByNameContainingIgnoreCase(String name);
}