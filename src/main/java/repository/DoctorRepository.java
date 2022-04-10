package repository;

import entity.Doctor;
import java.util.List;

public interface DoctorRepository {
    List<Doctor> findAll();
    void truncate();
    Doctor findByNationalCode(String nationalCode);
    List<Doctor> findByClinicId(Integer clinicId);
}
