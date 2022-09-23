package com.sm.ems.department.service;

import com.sm.ems.department.entity.Department;
import com.sm.ems.department.error.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    Department saveDepartment(Department department);

    Department findDepartmentById(Long id) throws DepartmentNotFoundException;

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);

    List<Department> fetchAllDepartments();

    Department findDepartmentByName(String departmentName);
}
