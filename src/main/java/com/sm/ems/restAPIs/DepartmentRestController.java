package com.sm.ems.restAPIs;

import com.sm.ems.department.entity.Department;
import com.sm.ems.department.error.DepartmentNotFoundException;
import com.sm.ems.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@CrossOrigin
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Department saveDepartment(Department department) {
        return departmentService.saveDepartment(department);
    }

    @Operation(summary = "Fetch Department based on Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully Fetched Department based on Id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)})

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id) throws DepartmentNotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @PutMapping(value = "/update/{id}")
    public Department updateDepartment(Department department) {
        return departmentService.updateDepartment(department.getId(), department);

    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }


    @GetMapping("/viewAll")
    public List<Department> fetchAllDepartments(Model model) {
        return departmentService.fetchAllDepartments();

    }

    @GetMapping("/name/{name}")
    public Department findDepartmentByName(@PathVariable String name) {
        return departmentService.findDepartmentByName(name);

    }

}
