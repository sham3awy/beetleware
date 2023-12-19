package com.example.appointments.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointments.model.Appointment;
import com.example.appointments.model.Patient;
import com.example.appointments.repository.AppointmentRepository;
import com.example.appointments.repository.PatientRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public void createAppointment(Appointment appointment) {
        LocalDateTime appointmentDateTime = appointment.getDateTime();
        Optional<Appointment> existingAppointment = appointmentRepository.findByDateTime(appointmentDateTime);
        if (existingAppointment.isPresent()) {
            throw new RuntimeException("An appointment already exists at this time for the patient.");
        }
        else {
        	appointmentRepository.save(appointment);
        }
    }

    public void updateAppointment(Long appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        existingAppointment.setDateTime(updatedAppointment.getDateTime());
        existingAppointment.setCancellationReason(updatedAppointment.getCancellationReason());

        appointmentRepository.save(existingAppointment);
    }
    
    public List<Appointment> findAppointmentsByDateFuture(LocalDateTime dateTime) {
        return appointmentRepository.findAppointmentsByDateFuture(dateTime);
    }
    
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    public List<Appointment> findAppointmentsByDateHistory(LocalDateTime dateTime) {
        return appointmentRepository.findAppointmentsByDateHistory(dateTime);
    }

    public List<Appointment> findAppointmentsByPatientName(String name) {
        return appointmentRepository.findByPatientNameContainingIgnoreCase(name);
    }
    
    public List<Appointment> getPatientAppointmentsHistory(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patient.getAppointments();
    }

    public List<Appointment> findTodayAppointments() {
        return appointmentRepository.findTodayAppointments();
    }

    public List<Appointment> findAppointmentsByCancellationReason(String reason) {
        return appointmentRepository.findByCancellationReason(reason);
    }

    public void cancelAppointment(Long appointmentId, String cancellationReason) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setCancellationReason(cancellationReason);
        appointmentRepository.save(appointment);
    }
}
