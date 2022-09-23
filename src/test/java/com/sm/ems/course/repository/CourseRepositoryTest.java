package com.sm.ems.course.repository;

//import static org.junit.jupiter.api.Assertions.*;

import com.sm.ems.course.entity.Course;
import com.sm.ems.lecturer.entity.Lecturer;
import com.sm.ems.student.entity.Student;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Order(1)
    public void printAllCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }


    @Test
    @Order(2)
    public void findAllPagination() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Pageable secodPageWithOneElement = PageRequest.of(1, 1);

        List<Course> courses = courseRepository.findAll(firstPageWithTwoElements).getContent();
        System.out.println("courses = " + courses);

        long totalElements = courseRepository.findAll(firstPageWithTwoElements).getTotalElements();
        System.out.println("totalElements = " + totalElements);

        long totalPages = courseRepository.findAll(firstPageWithTwoElements).getTotalPages();
        System.out.println("totalPages = " + totalPages);
    }

    @Test
    @Order(3)
    public void findAllSorting() {

        Pageable sortByName = PageRequest.of(0, 4, Sort.by("name"));
        List<Course> coursesSortByName = courseRepository.findAll(sortByName).getContent();
        System.out.println("coursesSortByName = " + coursesSortByName);

        Pageable sortByCredits = PageRequest.of(0, 4, Sort.by("credits"));
        List<Course> coursesSortedByCredit = courseRepository.findAll(sortByCredits).getContent();
        System.out.println("coursesSortedByCredit = " + coursesSortedByCredit);

        Pageable sortByCreditAndName = PageRequest.of(0, 5, Sort.by("credits").descending().and(Sort.by("name")));
        List<Course> coursesSortedByCreditAndName = courseRepository.findAll(sortByCreditAndName).getContent();
        System.out.println("sortByCreditAndName = " + sortByCreditAndName);
        System.out.println("coursesSortedByCreditAndName = " + coursesSortedByCreditAndName);
    }

    @Test
    @Order(4)
    public void printCoursesByNamesContaining() {

        String name = "T";
        List<Course> pagesContainingName = courseRepository.findCoursesByNameContaining(name, PageRequest.of(0, 5)).getContent();
        System.out.println("pagesContainingName = " + pagesContainingName);

    }

    @Test
    @Order(5)
    public void saveCourseWithStudentAndLecturer() {

        Lecturer lecturer = Lecturer.builder()
                .firstName("zee")
                .lastName("joe")
                .build();


        Student student = Student.builder()
                .firstName("km")
                .lastName("rb")
                .address("galle")
                .emailId("nkm@gmail.com")
                .build();


        Course course = Course.builder()
                .name("AI")
                .code("AI-001")
                .credits(50)
                .build();

        course.addStudent(student);
        courseRepository.save(course);
    }

}