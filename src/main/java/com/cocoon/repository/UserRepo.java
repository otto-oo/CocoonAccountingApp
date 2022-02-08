package com.cocoon.repository;

import com.cocoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findAllByCompanyId(Long id);
}
