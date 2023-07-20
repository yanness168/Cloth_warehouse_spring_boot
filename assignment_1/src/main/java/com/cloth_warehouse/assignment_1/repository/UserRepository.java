package com.cloth_warehouse.assignment_1.repository;

import com.cloth_warehouse.assignment_1.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
