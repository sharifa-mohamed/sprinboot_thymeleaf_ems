package com.sm.ems.student.controller;

import com.sm.ems.course.entity.Course;
import com.sm.ems.course.service.CourseService;
import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.student.entity.Student;
import com.sm.ems.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public String saveStudent(@Valid Student student, Errors errors, Model model) {

        Student dbStudent = studentService.findByEmailId(student.getEmailId());

        if (dbStudent != null) {
            errors.rejectValue("emailId", "err.emailExists", "Student with given email id already exists!!");
        }

        if (errors == null || errors.getErrorCount() == 0) {
            Set<Course> courses = student.getCourses();
            student = studentService.saveStudent(student);

            for (Course course : courses) {
                course.addStudent(student);
                courseService.saveCourse(course);
            }


            model.addAttribute("messsage", "Student registered successfully!!");
            return "redirect:/student/viewAll";
        }


        model.addAttribute("courses", courseService.fetchAllCourses());
        return "student/addStudent";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.fetchAllCourses());
        return "student/addStudent";
    }


    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.findStudentById(id));
        model.addAttribute("courses", courseService.fetchAllCourses());
        return "student/editStudent";

    }

    @GetMapping("/view/{id}")
    public String viewStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("courses", student.getCourses());
        return "student/viewStudent";

    }


    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateStudent(@Valid Student student, Errors errors, Model model) {
        if (errors == null || errors.getErrorCount() == 0) {
            Set<Course> courses = student.getCourses();
            student = studentService.updateStudent(student.getId(), student);

            for (Course course : courses) {
                course.addStudent(student);
                courseService.saveCourse(course);
            }

            model.addAttribute("messsage", "Student updated successfully!!");
            return "redirect:/student/viewAll";
        }

        model.addAttribute("courses", courseService.fetchAllCourses());
        return "student/addStudent";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);
        return "redirect:/student/viewAll";
    }


    @GetMapping("/viewAll")
    public String fetchAllStudents(Model model) {
        model.addAttribute("students", studentService.fetchAllStudents());
        return "student/viewAllStudents";

    }

    @GetMapping("/name/{name}")
    public Student findStudentByName(@PathVariable String name) {
        return studentService.findStudentByName(name);

    }

}
