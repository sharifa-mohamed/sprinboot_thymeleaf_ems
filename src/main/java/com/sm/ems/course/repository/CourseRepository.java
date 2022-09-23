package com.sm.ems.course.repository;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findCoursesByNameContaining(String name, Pageable pageable);

    Course findCourseByNameContaining(String name);

    @Query("select new com.sm.ems.chart.model.ChartData (c.name,count(s)) from Course c join c.students s where c.department.id=3 group by c.name")
    public List<ChartData> getChart();

    @Query("select new com.sm.ems.chart.model.ChartData (c.name,count(s)) from Course c join c.students s where c.department.id=:departmentId group by c.name")
    List<ChartData> getCourseStudentCountOfDepartment(@Param("departmentId") Long departmentId);

}
