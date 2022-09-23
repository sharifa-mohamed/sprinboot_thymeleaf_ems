package com.sm.ems.security.service;


import com.sm.ems.security.entity.Role;
import com.sm.ems.security.entity.User;
import com.sm.ems.security.repository.RoleRepository;
import com.sm.ems.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Role> findAll() {
        return roleRepository.findAll();
    }


    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }


    public void delete(Long id) {
        roleRepository.deleteById(id);
    }


    public void save(Role role) {
        roleRepository.save(role);
    }


    public void assignUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }


    public void unassignUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        user.getRoles().removeIf(x -> x.getId() == roleId);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    public Set<Role> geUserRoles(User user) {
        return user.getRoles();
    }

    public List<Role> getUserNotRoles(User user) {
        return roleRepository.getUserNotRoles(user.getId());
    }

}
