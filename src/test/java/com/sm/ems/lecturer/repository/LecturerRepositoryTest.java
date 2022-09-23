package com.sm.ems.lecturer.repository;


import com.sm.ems.course.repository.CourseRepository;
import com.sm.ems.lecturer.entity.Lecturer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LecturerRepositoryTest {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Order(1)
    public void saveLecturer() {


        Lecturer lecturer = Lecturer.builder()
                .firstName("Gaya")
                .lastName("Silva")
                .courses(courseRepository.findAll())
                .build();

        lecturerRepository.save(lecturer);

    }

    public void printAllLecturers() {

        List<Lecturer> lecturerList = lecturerRepository.findAll();
        System.out.println("lecturerList = " + lecturerList);
    }
}