package com.AlNada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AlNada.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
