package repository;

import entity.Doctor;

public interface DoctorRepository {

    Doctor findByNationalCode(String nationalCode);
}
