package com.example.appointments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointments.model.Patient;
import com.example.appointments.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    // Other methods related to Patient service
}