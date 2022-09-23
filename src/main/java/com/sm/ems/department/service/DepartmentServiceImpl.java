package com.sm.ems.department.service;


import com.sm.ems.department.entity.Department;
import com.sm.ems.department.error.DepartmentNotFoundException;
import com.sm.ems.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);

    }

    public Department findDepartmentById(Long id) throws DepartmentNotFoundException {

        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent())
            throw new DepartmentNotFoundException("Department not found with id " + id);
        return department.get();
    }

    public Department updateDepartment(Long id, Department department) {
        Department deptFromDb = departmentRepository.findById(id).get();

        if (Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
            deptFromDb.setName(department.getName());
        }

        if (Objects.nonNull(department.getCode()) && !"".equalsIgnoreCase(department.getCode())) {
            deptFromDb.setCode(department.getCode());
        }


        if (Objects.nonNull(department.getAddress()) && !"".equalsIgnoreCase(department.getAddress())) {
            deptFromDb.setAddress(department.getAddress());
        }

        return departmentRepository.save(deptFromDb);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findDepartmentByNameIgnoreCase(departmentName);
    }
}
