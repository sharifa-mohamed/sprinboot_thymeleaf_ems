package com.sm.ems.restAPIs;

import com.sm.ems.course.service.CourseService;
import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.student.entity.Student;
import com.sm.ems.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentRestController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Student saveStudent(Student student) {
        return studentService.saveStudent(student);

    }

    @PutMapping(value = "/update/{id}")
    public Student updateStudent(Student student) {
        return studentService.updateStudent(student.getId(), student);

    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

    }


    @GetMapping("/viewAll")
    public List<Student> fetchAllStudents(Model model) {
        return studentService.fetchAllStudents();

    }

    @GetMapping("/name/{name}")
    public Student findStudentByName(@PathVariable String name) {
        return studentService.findStudentByName(name);

    }

}
