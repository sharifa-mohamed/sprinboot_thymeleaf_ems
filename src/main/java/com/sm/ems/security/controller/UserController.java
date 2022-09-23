package com.sm.ems.security.controller;

import com.sm.ems.security.entity.User;
import com.sm.ems.security.service.RoleService;
import com.sm.ems.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public String addUser(@Valid User user, Errors errors, Model model) {

        User dbUser = userService.findByUsername(user.getUsername());

        if (dbUser != null) {

            errors.rejectValue("username", "err.nameTaken", "Username exists!!");
        }
      
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "err.passwordMismatch", "Password & confim password must match!!");
        }

        if (errors == null || errors.getErrorCount() == 0) {
            userService.save(user);
            model.addAttribute("message", "User registered successfully. Please Login.");
            return "security/login";
        }
        return "security/register";

    }

    @GetMapping("/viewAll")
    public String fetchAllUsers(Model model) {
        model.addAttribute("users", userService.fetchAllUsers());
        return "security/viewAllUsers";
    }

    @GetMapping("/addUnRegistered")
    public String addUnRegistered(Model model) {
        model.addAttribute("user", new User());
        return "security/addUnRegistered";
    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "security/editUser";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteUser(@PathVariable Long id) {

        userService.delete(id);
        return "redirect:/user/viewAll";
    }


}
