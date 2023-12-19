package com.example.appointments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointments.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Custom query to find patient by name
    List<Patient> findByNameContainingIgnoreCase(String name);
}