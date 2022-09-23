package com.sm.ems.lecturer.repository;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.lecturer.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Lecturer findLecturerByFirstNameOrLastNameContaining(String firstName, String lastName);

    //// get lecturer count in department grouped by qualification

    //@Query("select new com.sm.ems.chart.model.ChartData (c.name,count(s)) from Course c join c.students s where c.department.id=:departmentId group by c.name")
    //

    @Query(value = "select new com.sm.ems.chart.model.ChartData(l.qualification.name, count(q)) from Lecturer l join l.qualification q where l.department.id=:departmentId  group by l.qualification")
    List<ChartData> getLecturerCountInDepartmentGroupedByQualification(@Param("departmentId") Long departmentId);

    @Query(value = "select new com.sm.ems.chart.model.ChartData(l.qualification.name, count(q)) from Lecturer l join l.qualification q group by l.qualification")
    List<ChartData> getLecturerCountGroupedByQualification();

}
