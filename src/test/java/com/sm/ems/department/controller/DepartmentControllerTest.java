package com.sm.ems.department.controller;

import com.sm.ems.department.entity.Department;
import com.sm.ems.department.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department outDepartment;

    @BeforeEach
    void setUp() {
        outDepartment = Department.builder()
                .address("Ahmedabad")
                .code("IT-06")
                .name("IT")
                .id(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .address("Ahmedabad")
                .code("IT-06")
                .name("IT")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(outDepartment);

        mockMvc.perform(post("/department/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\":\"IT\",\n" +
                                "\t\"address\":\"Ahmedabad\",\n" +
                                "\t\"code\":\"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.findDepartmentById(1L))
                .thenReturn(outDepartment);

        mockMvc.perform(get("/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").
                        value(outDepartment.getName()));
    }
}