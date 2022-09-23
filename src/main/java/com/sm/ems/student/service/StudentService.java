package com.sm.ems.student.service;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.student.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    Student findStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    List<Student> fetchAllStudents();

    Student findStudentByName(String name);

    Student findByEmailId(String emailId);


    List<ChartData> getStudentCountGroupedByYearChart();

}
