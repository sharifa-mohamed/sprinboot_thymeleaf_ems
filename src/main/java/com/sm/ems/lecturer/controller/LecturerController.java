package com.sm.ems.lecturer.controller;

import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.lecturer.entity.Lecturer;
import com.sm.ems.lecturer.service.LecturerService;
import com.sm.ems.lecturer.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {


    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private QualificationService qualificationService;

    @PostMapping("/add")
    public String saveLecturer(@Valid Lecturer lecturer) {
        lecturerService.saveLecturer(lecturer);
        return "redirect:/lecturer/viewAll";
    }

    @GetMapping("/add")
    public String addLecturerForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        addList(model);
        return "lecturer/addLecturer";
    }

    private void addList(Model model) {
        model.addAttribute("departments", departmentService.fetchAllDepartments());
        model.addAttribute("qualifications", qualificationService.fetchAllQualifications());
    }

    @GetMapping("/edit/{id}")
    public String editLecturerForm(@PathVariable Long id, Model model) {
        model.addAttribute("lecturer", lecturerService.findLecturerById(id));
        addList(model);
        return "lecturer/editLecturer";

    }

    @GetMapping("/view/{id}")
    public String viewLecturerForm(@PathVariable Long id, Model model) {
        model.addAttribute("lecturer", lecturerService.findLecturerById(id));
        return "lecturer/viewLecturer";

    }


    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateLecturer(@Valid Lecturer lecturer) {
        lecturerService.updateLecturer(lecturer.getId(), lecturer);
        return "redirect:/lecturer/viewAll";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);
        return "redirect:/lecturer/viewAll";
    }


    @GetMapping("/viewAll")
    public String fetchAllLecturers(Model model) {
        model.addAttribute("lecturers", lecturerService.fetchAllLecturers());
        return "lecturer/viewAllLecturers";

    }

    @GetMapping("/name/{name}")
    public Lecturer findLecturerByName(@PathVariable String name) {
        return lecturerService.findLecturerByName(name, name);

    }

}
