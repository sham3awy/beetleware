package com.example.appointments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointments.model.Doctor;
import com.example.appointments.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findDoctorsByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }

    // Other methods related to Doctor service
}
