package com.sm.ems.restAPIs;

import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.lecturer.entity.Lecturer;
import com.sm.ems.lecturer.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturer")
@CrossOrigin
public class LecturerRestController {


    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/add")
    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerService.saveLecturer(lecturer);
    }

    @PutMapping(value = "/update/{id}")
    public Lecturer updateLecturer(Lecturer lecturer) {
        return lecturerService.updateLecturer(lecturer.getId(), lecturer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);
    }

    @GetMapping("/viewAll")
    public List<Lecturer> fetchAllLecturers() {
        return lecturerService.fetchAllLecturers();

    }

    @GetMapping("/name/{name}")
    public Lecturer findLecturerByName(@PathVariable String name) {
        return lecturerService.findLecturerByName(name, name);

    }

}
