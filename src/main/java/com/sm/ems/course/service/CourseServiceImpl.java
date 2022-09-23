package com.sm.ems.course.service;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.course.entity.Course;
import com.sm.ems.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    public Course updateCourse(Long id, Course course) {
        return courseRepository.save(course);

    }

    public void deleteCourse(Long id) {
        
        courseRepository.deleteById(id);
    }

    public List<Course> fetchAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseByName(String name) {
        return courseRepository.findCourseByNameContaining(name);
    }

    @Override
    public List<ChartData> getChart() {
        return courseRepository.getChart();
    }

    @Override
    public List<ChartData> getChartCourseStudentCountOfDepartment(Long departmentId) {
        return courseRepository.getCourseStudentCountOfDepartment(departmentId);
    }
}
