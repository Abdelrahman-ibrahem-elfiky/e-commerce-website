package com.project.Ecommerce.repository;

import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}
