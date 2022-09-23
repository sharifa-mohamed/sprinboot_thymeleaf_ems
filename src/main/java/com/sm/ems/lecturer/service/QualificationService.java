package com.sm.ems.lecturer.service;

import com.sm.ems.lecturer.entity.Qualification;

import java.util.List;

public interface QualificationService {
    List<Qualification> fetchAllQualifications();

    Qualification save(Qualification qualification);

    Qualification findById(Long id);
}
