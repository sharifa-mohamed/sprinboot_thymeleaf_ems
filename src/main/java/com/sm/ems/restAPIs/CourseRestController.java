package com.sm.ems.restAPIs;

import com.sm.ems.course.entity.Course;
import com.sm.ems.course.service.CourseService;
import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.lecturer.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseRestController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LecturerService lecturerService;


    @PostMapping("/add")
    public Course saveCourse(Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping(value = "/update/{id}")
    public Course updateCourse(Course course) {
        return courseService.updateCourse(course.getId(), course);

    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/viewAll")
    public List<Course> fetchAllCourses(Model model) {
        return courseService.fetchAllCourses();
    }

    @GetMapping("/name/{name}")
    public Course findCourseByName(@PathVariable String name) {
        return courseService.findCourseByName(name);
    }

}
