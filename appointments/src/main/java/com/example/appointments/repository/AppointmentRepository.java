package com.example.appointments.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.appointments.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE DATE(a.dateTime) >= :dateTime")
    List<Appointment> findAppointmentsByDateFuture(@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT a FROM Appointment a WHERE DATE(a.dateTime) <= :dateTime")
    List<Appointment> findAppointmentsByDateHistory(@Param("dateTime") LocalDateTime dateTime);

    List<Appointment> findByPatientNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Appointment a WHERE DATE(a.dateTime) = DATE(CURRENT_TIMESTAMP)")
    List<Appointment> findTodayAppointments();
    
    Optional<Appointment> findByDateTime(LocalDateTime dateTime);

    @Query("SELECT a FROM Appointment a WHERE a.cancellationReason like %:reason%")
    List<Appointment> findByCancellationReason(@Param("reason") String reason);
}