package com.sm.ems.department.repository;

import com.sm.ems.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByName(String departmentName);

    Department findDepartmentByNameIgnoreCase(String departmentName);
}
