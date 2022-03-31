package repository;

import entity.Clinic;

public interface ClinicRepository {
    Clinic findByName(String name);
}
