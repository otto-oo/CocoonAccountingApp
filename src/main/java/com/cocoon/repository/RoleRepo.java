package com.cocoon.repository;

import com.cocoon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleById(Long id);
}
