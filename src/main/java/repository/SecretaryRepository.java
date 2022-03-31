package repository;

import entity.Secretary;

public interface SecretaryRepository {
    void truncate();

    Secretary findByNationalCode(String nationalCode);
}
