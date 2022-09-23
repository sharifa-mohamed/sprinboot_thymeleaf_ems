package com.sm.ems.department.controller;

import com.sm.ems.department.entity.Department;
import com.sm.ems.department.error.DepartmentNotFoundException;
import com.sm.ems.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public String saveDepartment(Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/department/viewAll";
    }

    @GetMapping("/add")
    public String addDepartmentForm() {
        return "department/addDepartment";
    }

    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable Long id, Model model) throws DepartmentNotFoundException {
        model.addAttribute("department", departmentService.findDepartmentById(id));
        return "department/editDepartment";

    }

    @GetMapping("/view/{id}")
    public String viewDepartmentForm(@PathVariable Long id, Model model) throws DepartmentNotFoundException {
        model.addAttribute("department", departmentService.findDepartmentById(id));
        return "department/viewDepartment";

    }


    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id) throws DepartmentNotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateDepartment(Department department) {
        departmentService.updateDepartment(department.getId(), department);
        return "redirect:/department/viewAll";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/department/viewAll";
    }


    @GetMapping("/viewAll")
    public String fetchAllDepartments(Model model) {
        model.addAttribute("departments", departmentService.fetchAllDepartments());
        return "department/viewAllDepartments";

    }

    @GetMapping("/name/{name}")
    public Department findDepartmentByName(@PathVariable String name) {
        return departmentService.findDepartmentByName(name);

    }

}
