package com.example.spring.boot.projekt.Efremov.service;

import com.example.spring.boot.projekt.Efremov.dao.RoleDao;
import com.example.spring.boot.projekt.Efremov.modal.Role;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RoleService {
    //@Autowired lombok constructor
    private final RoleDao roleRepository;

/*
    // @Autowired
    public RoleService(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }
*/

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

 /*   public Role getRoleByName(String name) {
        return roleRepository.findByRole(name);
    }*/

}

