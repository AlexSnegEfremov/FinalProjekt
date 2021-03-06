package com.example.spring.boot.projekt.Efremov.dao;

import com.example.spring.boot.projekt.Efremov.modal.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
       Role findByRole(String role);
}
