package com.sm.ems.course.service;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.course.entity.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);

    Course findCourseById(Long id);

    Course updateCourse(Long id, Course course);

    void deleteCourse(Long id);

    List<Course> fetchAllCourses();

    Course findCourseByName(String name);

    List<ChartData> getChart();

    List<ChartData> getChartCourseStudentCountOfDepartment(Long department);
}
