package com.example.appointments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointments.model.Doctor;
import com.example.appointments.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/search")
    public List<Doctor> findDoctorsByName(@RequestParam String name) {
        return doctorService.findDoctorsByName(name);
    }

    // Other REST endpoints related to Doctor entity
}