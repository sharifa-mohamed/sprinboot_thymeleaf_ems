package com.sm.ems.course.repository;

import com.sm.ems.course.entity.Course;
import com.sm.ems.course.entity.CourseMaterial;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    @Order(1)
    public void saveCourseMaterial() {

        Course course = Course.builder()
                .name("IT")
                .code("IT-001")
                .credits(30)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course)
                .url("www.tutorialspoint.com")
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    @Order(2)
    public void printAllCourseMaterial() {

        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findAll();
      
        //assertNotNull(courseMaterialList);

    }

}