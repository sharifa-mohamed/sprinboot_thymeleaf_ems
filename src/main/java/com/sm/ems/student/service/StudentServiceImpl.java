package com.sm.ems.student.service;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.student.entity.Student;
import com.sm.ems.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Long id, Student student) {
        return studentRepository.save(student);

    }

    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }

    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentByName(String name) {
        return studentRepository.findStudentByFirstNameOrLastNameContaining(name, name);
    }

    @Override
    public Student findByEmailId(String emailId) {
        return studentRepository.findStudentByEmailId(emailId);
    }

    @Override
    public List<ChartData> getStudentCountGroupedByYearChart() {
        return studentRepository.getStudentCountGroupedByYearChart();
    }
}
