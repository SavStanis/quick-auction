package com.savstanis.quickauction.repository;

import com.savstanis.quickauction.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
