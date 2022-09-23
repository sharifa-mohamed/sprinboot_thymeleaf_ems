package com.sm.ems.security.controller;


import com.sm.ems.security.entity.Role;
import com.sm.ems.security.service.RoleService;
import com.sm.ems.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/viewAll")
    public String viewAllRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "security/roles";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Role getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping("/add")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/role/viewAll";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/security/roles";
    }

    @RequestMapping("/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Long userId,
                             @PathVariable Long roleId) {
        roleService.assignUserRole(userId, roleId);
        return "redirect:/user/update/" + userId;
    }

    @RequestMapping("/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Long userId,
                               @PathVariable Long roleId) {
        roleService.unassignUserRole(userId, roleId);
        return "redirect:/user/update/" + userId;
    }
}
