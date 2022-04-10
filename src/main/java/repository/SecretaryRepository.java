package repository;

import entity.Secretary;

import java.util.List;

public interface SecretaryRepository {
    void truncate();
    List<Secretary> findAll();
    Secretary findByNationalCode(String nationalCode);
}
