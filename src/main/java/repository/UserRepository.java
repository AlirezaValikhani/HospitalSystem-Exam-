package repository;

import entity.baseEntity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUserName(String userName);
    void truncate();
}
