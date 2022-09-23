package com.sm.ems.course.controller;

import com.sm.ems.course.entity.Course;
import com.sm.ems.course.service.CourseService;
import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.lecturer.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LecturerService lecturerService;


    @PostMapping("/add")
    public String saveCourse(Course course) {

        courseService.saveCourse(course);
        return "redirect:/course/viewAll";
    }

    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        addDeptAndLecturerToModel(model);
        return "course/addCourse";
    }

    private void addDeptAndLecturerToModel(Model model) {
        model.addAttribute("departments", departmentService.fetchAllDepartments());
        model.addAttribute("lecturers", lecturerService.fetchAllLecturers());
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        addDeptAndLecturerToModel(model);
        model.addAttribute("course", courseService.findCourseById(id));
        return "course/editCourse";

    }

    @GetMapping("/view/{id}")
    public String viewCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.findCourseById(id));
        return "course/viewCourse";

    }


    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCourse(Course course) {
        courseService.updateCourse(course.getId(), course);
        return "redirect:/course/viewAll";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);
        return "redirect:/course/viewAll";
    }


    @GetMapping("/viewAll")
    public String fetchAllCourses(Model model) {
        model.addAttribute("courses", courseService.fetchAllCourses());
        return "course/viewAllCourses";

    }

    @GetMapping("/name/{name}")
    public Course findCourseByName(@PathVariable String name) {
        return courseService.findCourseByName(name);

    }

}
