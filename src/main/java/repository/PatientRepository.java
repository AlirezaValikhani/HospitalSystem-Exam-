package repository;

import entity.Clinic;
import entity.Patient;
import entity.Prescription;

import java.util.List;

public interface PatientRepository {
    List<Patient> findAll();
    void truncate();
    Patient findByNationalCode(String nationalCode);
}
