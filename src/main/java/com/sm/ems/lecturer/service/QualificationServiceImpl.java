package com.sm.ems.lecturer.service;

import com.sm.ems.lecturer.entity.Qualification;
import com.sm.ems.lecturer.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public List<Qualification> fetchAllQualifications() {
        return qualificationRepository.findAll();

    }

    @Override
    public Qualification save(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    public Qualification findById(Long id) {
        return qualificationRepository.findById(id).get();
    }

}
