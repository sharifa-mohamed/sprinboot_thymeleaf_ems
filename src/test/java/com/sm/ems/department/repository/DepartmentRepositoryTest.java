package com.sm.ems.department.repository;

import com.sm.ems.department.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .name("Information Technology")
                .address("Mountlavinia")
                .code("IT-002")
                //.id(1L)
                .build();

        entityManager.persist(department);
    }

    @Test
    public void whenValidDepartmentName_thenDataShouldBeFound() {
        String departmentName = "Information Technology";
        Department departmentFromDB = departmentRepository.findDepartmentByName(departmentName);
        assertThat(departmentFromDB.getName()).isEqualTo(departmentName);

    }
}