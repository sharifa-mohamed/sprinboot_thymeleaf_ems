package com.sm.ems.lecturer.service;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.lecturer.entity.Lecturer;

import java.util.List;

public interface LecturerService {
    List<Lecturer> fetchAllLecturers();

    Lecturer saveLecturer(Lecturer lecturer);

    Lecturer findLecturerById(Long id);

    Lecturer updateLecturer(Long id, Lecturer lecturer);

    void deleteLecturer(Long id);

    //   Lecturer findLecturerByName(String name);

    Lecturer findLecturerByName(String firstName, String lastName);


    List<ChartData> getLecturerCountInDepartmentGroupedByQualification(Long departmentId);

    List<ChartData> getLecturerCountGroupedByQualification();
}
