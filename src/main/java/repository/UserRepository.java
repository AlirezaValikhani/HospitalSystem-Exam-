package repository;

import entity.baseEntity.User;

public interface UserRepository {
    User findByUserName(String userName);
}
