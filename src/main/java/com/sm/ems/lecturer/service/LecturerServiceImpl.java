package com.sm.ems.lecturer.service;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.lecturer.entity.Lecturer;
import com.sm.ems.lecturer.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    LecturerRepository lecturerRepository;

    @Override
    public List<Lecturer> fetchAllLecturers() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Lecturer findLecturerById(Long id) {
        return lecturerRepository.findById(id).get();
    }

    @Override
    public Lecturer updateLecturer(Long id, Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }

    @Override
    public Lecturer findLecturerByName(String firstName, String lastName) {
        return lecturerRepository.findLecturerByFirstNameOrLastNameContaining(firstName, lastName);
    }

    @Override
    public List<ChartData> getLecturerCountInDepartmentGroupedByQualification(Long departmentId) {
        return lecturerRepository.getLecturerCountInDepartmentGroupedByQualification(departmentId);
    }

    @Override
    public List<ChartData> getLecturerCountGroupedByQualification() {
        return lecturerRepository.getLecturerCountGroupedByQualification();
    }
}
