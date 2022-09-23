package com.sm.ems.student.repository;


import com.sm.ems.student.entity.Guardian;
import com.sm.ems.student.entity.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    public void saveStudent() {


        Student s = Student.builder()
                .firstName("rm")
                .lastName("mm")
                .address("colombo")
                .emailId("rm@gmail.com")

                .build();

        studentRepository.save(s);

        Student s2 = Student.builder()
                .firstName("km")
                .lastName("pm")
                .address("dehiwala")
                .emailId("kmpm@gmail.com")

                .build();

        studentRepository.save(s2);

    }

    @Test
    @Order(2)
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("riya")
                .phone("0734443433")
                .email("riya@gmail.com")
                .build();

        Student s = Student.builder()
                .firstName("sm")
                .lastName("mm")
                .address("colombo")
                .emailId("sm@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(s);
    }

    @Test
    @Order(3)
    public void printListOfStudents() {

        List<Student> studentsList = studentRepository.findAll();
        System.out.println("studentsList = " + studentsList);


    }


    @Test
    @Order(4)
    public void printStudentsByFirstName() {
        String firstName = "sm";
        List<Student> students = studentRepository.findByFirstName(firstName);
        System.out.println("students = " + students);

    }

    @Test
    @Order(5)
    public void printStudentByFirstNameContaining() {
        String firstNameContaining = "m";
        List<Student> studentList = studentRepository.findStudentByFirstNameContainingIgnoreCase(firstNameContaining);
        System.out.println("studentList = " + studentList);
    }


    @Test
    @Order(6)
    public void printStudentsWithGuardians() {

        List<Student> studentList = studentRepository.findStudentByGuardianNameNotNull();
        System.out.println("studentList = " + studentList);
    }


    @Test
    @Order(7)
    public void printGetStudentsByFirstNameAndLastName() {
        String firstName = "sm";
        String lastName = "om";
        List<Student> studentList = studentRepository.findStudentsByFirstNameAndLastName(firstName, lastName);
        System.out.println("studentList = " + studentList);

    }


    @Test
    @Order(8)
    public void printStudentsByGuardianEmailAddress() {
        String email = "riya@gmail.com";
        List<Student> studentList = studentRepository.getStudentsByGuardianEmailId(email);
        System.out.println("studentList = " + studentList);
    }

    @Test
    @Order(9)
    public void printStudentsByAddress() {
        String address = "colombo";
        List<Student> studentList = studentRepository.getStudentsByAddress(address);
        System.out.println("studentList = " + studentList);
    }

    @Test
    @Order(10)
    public void getStudentByFirstName() {
        String firstName = "sm";
        List<Student> students = studentRepository.getStudentByFirstName(firstName);
        System.out.println("students = " + students);

    }


    @Test
    @Order(11)
    @Transactional
    @Rollback(false)
    public void updateEmailByFirstName() {
        String firstName = "sm";
        String email = "sm2@gmail.com";
        //studentRepository.setSafeUpdateFalse();
        int res = studentRepository.updateStudentEmailByFirstName(firstName, email);
        //studentRepository.setSafeUpdateTrue();
        System.out.println("res = " + res);
    }

    @Test
    @Order(12)
    public void getStudentByFirstName2() {
        String firstName = "sm";
        List<Student> students = studentRepository.getStudentByFirstName(firstName);
        System.out.println("students = " + students);

    }


    @Test
    @AfterAll
    @Transactional
    @Disabled

    public void truncateStudentTable() {
//https://newbedev.com/is-it-possible-to-use-truncate-in-spring-data-jpa-using-jparepository-or-a-more-effective-method-than-the-standard-deleteall
        studentRepository.truncateStudentTable();
        System.out.println("Student table truncated");
    }


}