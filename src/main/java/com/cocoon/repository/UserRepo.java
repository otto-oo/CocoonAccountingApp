package com.cocoon.repository;

import com.cocoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "select * from company c\n" +
            "join users u on c.id = u.company_id\n" +
            "where c.title = ?1 and u.role_id = 1", nativeQuery = true)

    User findByEmail(String email);

    List<User> findAllByCompanyId(Long id);
}
