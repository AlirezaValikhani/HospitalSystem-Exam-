package repository;

import entity.Clinic;
import entity.Prescription;

import java.util.List;

public interface PrescriptionRepository {
    List<Prescription> findAll();
    void truncate();
    List<Prescription> findPrescriptionByUserId(Integer id);
}
