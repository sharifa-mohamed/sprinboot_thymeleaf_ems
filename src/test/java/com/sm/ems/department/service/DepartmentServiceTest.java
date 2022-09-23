package com.sm.ems.department.service;

import com.sm.ems.department.entity.Department;
import com.sm.ems.department.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DepartmentServiceTest {


    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {

        Department department = new Department().builder()
                .id(1L)
                .name("IT")
                .code("IT-002")
                .address("Galle")
                .build();

        Mockito.when(departmentRepository.findDepartmentByNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    @DisplayName("Get data when valid Department name")
    void whenValidDepartmentName_thenDepartmentShouldBeFound() {
        String departmentName = "IT";
        Department foundDepartment = departmentService.findDepartmentByName(departmentName);
        assertThat(foundDepartment.getName()).isEqualTo(departmentName);
    }

    @Test
    @Disabled
    void whenInvalidDepartmentName_thenDepartmentShouldNotBeFound() {
        String departmentName = "23";
        Department foundDepartment = departmentService.findDepartmentByName(departmentName);
        assertThat(foundDepartment).isEqualTo(null);
    }
}