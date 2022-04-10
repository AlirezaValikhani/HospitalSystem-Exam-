package repository;

import entity.Clinic;

import java.util.List;

public interface ClinicRepository {
    List<Clinic> findAll();
    void truncate();
    Clinic findByName(String name);
}
