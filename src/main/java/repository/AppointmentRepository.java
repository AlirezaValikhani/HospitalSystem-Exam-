package repository;

import entity.Appointment;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentRepository {
    List<Appointment> findAll();
    void truncate();
    Appointment findByTime(Timestamp timestamp);
}
