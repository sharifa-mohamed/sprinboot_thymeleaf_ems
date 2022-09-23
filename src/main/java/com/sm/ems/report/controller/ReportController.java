package com.sm.ems.report.controller;

import com.lowagie.text.DocumentException;
import com.sm.ems.course.entity.Course;
import com.sm.ems.course.service.CourseService;
import com.sm.ems.department.entity.Department;
import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.lecturer.entity.Lecturer;
import com.sm.ems.lecturer.service.LecturerService;
import com.sm.ems.report.PdfExporter;
import com.sm.ems.security.entity.User;
import com.sm.ems.security.service.UserService;
import com.sm.ems.student.entity.Student;
import com.sm.ems.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LecturerService lecturerService;


    @GetMapping("/users")
    public void getUsersReport(HttpServletResponse response) throws DocumentException, IOException, InvocationTargetException, IllegalAccessException {
        exportPDf(response, "users_", User.class, "List of Users", userService.fetchAllUsers());
    }

    @GetMapping("/students")
    public void getStudentsReport(HttpServletResponse response) throws DocumentException, IOException, InvocationTargetException, IllegalAccessException {
        exportPDf(response, "students_", Student.class, "List of Students", studentService.fetchAllStudents());
    }

    @GetMapping("/departments")
    public void getDepartmentsReport(HttpServletResponse response) throws DocumentException, IOException, InvocationTargetException, IllegalAccessException {
        exportPDf(response, "departments_", Department.class, "List of Departments", departmentService.fetchAllDepartments());
    }

    @GetMapping("/courses")
    public void getCoursesReport(HttpServletResponse response) throws DocumentException, IOException, InvocationTargetException, IllegalAccessException {
        exportPDf(response, "courses_", Course.class, "List of Courses", courseService.fetchAllCourses());
    }

    @GetMapping("/lecturers")
    public void getLecturersReport(HttpServletResponse response) throws DocumentException, IOException, InvocationTargetException, IllegalAccessException {
        exportPDf(response, "lecturers_", Lecturer.class, "List of Lecturers", lecturerService.fetchAllLecturers());
    }

    private void exportPDf(HttpServletResponse response, String reportName, Class reportClass, String title, List recordsList) throws IOException, InvocationTargetException, IllegalAccessException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";

        String headerValue = "inline; filename=" + reportName + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        PdfExporter exporter = new PdfExporter(reportClass, recordsList, title + " - " + currentDateTime);
        exporter.export(response);
    }


}
