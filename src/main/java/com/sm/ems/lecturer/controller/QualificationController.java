package com.sm.ems.lecturer.controller;

import com.sm.ems.lecturer.entity.Qualification;
import com.sm.ems.lecturer.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/viewAll")
    public String viewAllQualifications(Model model) {

        model.addAttribute("qualifications", qualificationService.fetchAllQualifications());
        return "lecturer/qualifications";
    }

    @PostMapping("/add")
    public String addQualification(Qualification qualification) {
        qualificationService.save(qualification);
        return "redirect:/qualification/viewAll";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Qualification getById(@PathVariable Long id) {
        return qualificationService.findById(id);
    }

}
