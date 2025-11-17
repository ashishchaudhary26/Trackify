package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Admin;
import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
         public List<Admin> findByUsernameAndPassword(String username, String password);
        public  Admin findByUsername(String username);
}
