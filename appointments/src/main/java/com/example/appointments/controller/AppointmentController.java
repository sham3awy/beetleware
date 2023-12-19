package com.example.appointments.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointments.model.Appointment;
import com.example.appointments.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    @PostMapping("/create")
    public void createAppointment(@RequestBody Appointment appointment) {
        appointmentService.createAppointment(appointment);
    }
    
    @PutMapping("/{appointmentId}/update")
    public void updateAppointment(@PathVariable Long appointmentId, @RequestBody Appointment appointment) {
        // Convert AppointmentDTO to Appointment entity and update the appointment
        // Perform necessary validations and business logic
        appointmentService.updateAppointment(appointmentId, appointment);
    }

    @GetMapping("/appointmentsByDateFuture")
    public List<Appointment> getAppointmentsByDateFuture(@RequestParam LocalDateTime dateTime) {
        return appointmentService.findAppointmentsByDateFuture(dateTime);
    }
    
    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.findAllAppointments();
    }
    
    @GetMapping("/appointmentsByDateHistory")
    public List<Appointment> getAppointmentsByDateHistory(@RequestParam LocalDateTime dateTime) {
        return appointmentService.findAppointmentsByDateHistory(dateTime);
    }
    
    @GetMapping("/patients/{patientId}/appointments")
    public List<Appointment> getPatientAppointmentsHistory(@PathVariable Long patientId) {
        return appointmentService.getPatientAppointmentsHistory(patientId);
    }

    @GetMapping("/appointmentsByPatient")
    public List<Appointment> getAppointmentsByPatientName(@RequestParam String name) {
        return appointmentService.findAppointmentsByPatientName(name);
    }

    @GetMapping("/today")
    public List<Appointment> getTodayAppointments() {
        return appointmentService.findTodayAppointments();
    }

    @GetMapping("/appointmentsByCancellationReason")
    public List<Appointment> getAppointmentsByCancellationReason(@RequestParam String reason) {
        return appointmentService.findAppointmentsByCancellationReason(reason);
    }

    @PostMapping("/{appointmentId}/cancel")
    public void cancelAppointment(@PathVariable Long appointmentId, @RequestParam String cancellationReason) {
        appointmentService.cancelAppointment(appointmentId, cancellationReason);
    }

    // Other REST endpoints related to Appointment entity
}