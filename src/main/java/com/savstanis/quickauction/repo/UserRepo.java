package com.savstanis.quickauction.repo;

import com.savstanis.quickauction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, String> {
}
