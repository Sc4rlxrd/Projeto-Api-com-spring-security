package com.me.scarlxrd.repository;

import com.me.scarlxrd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT e FROM User e JOIN FET CH e.roles WHERE e.username = (:username)")
    public User findByUsername(@Param("username") String username);
    boolean existsByUsername(String username);
}
