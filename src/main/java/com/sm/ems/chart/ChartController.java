package com.sm.ems.chart;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.course.service.CourseService;
import com.sm.ems.department.service.DepartmentService;
import com.sm.ems.lecturer.service.LecturerService;
import com.sm.ems.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LecturerService lecturerService;


    @GetMapping("/Department")
    public String viewDepartmentCharts(@RequestParam(value = "objectSelected", required = false) Long objectSelected, Model model) {

        if (objectSelected == null) {
            objectSelected = departmentService.fetchAllDepartments().get(0).getId();
        }

        model.addAttribute("objects", departmentService.fetchAllDepartments());
        model.addAttribute("objectSelected", objectSelected);
        model.addAttribute("chartName", "Department");

        setChartModel(model, "true", "Department Charts", List.of("Student count in each course in the department", "Lecturer count grouped by qualification in the department"),
                List.of("Courses", "Qualifications"), List.of("Student Count", "Lecturer Count"),
                List.of(courseService.getChartCourseStudentCountOfDepartment(objectSelected),
                        lecturerService.getLecturerCountInDepartmentGroupedByQualification(objectSelected)
                ));

        return "chart/barChart";
    }


    @GetMapping("/students")
    public String viewStudentChart(Model model) {

        setChartModel(model, "false", "Student Charts", List.of("Student count based on Registration Year"),
                List.of("Year"), List.of("Student Count"),
                List.of(studentService.getStudentCountGroupedByYearChart()));
        return "chart/barChart";
    }

    @GetMapping("/lecturers")
    public String viewLecturerChart(Model model) {
        setChartModel(model, "false", "Lecturer Charts", List.of("Lecturer count based on Qualification"),
                List.of("Qualification"), List.of("Lecturer Count"),
                List.of(lecturerService.getLecturerCountGroupedByQualification()));

        return "chart/barChart";
    }


    private void setChartModel(Model model, String withSelect, String pageTitle, List<String> chartTitles, List<String> xTitles, List<String> yTitles, List<List<ChartData>> chartDataList) {

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("withSelect", withSelect);
        int index = 1;
        for (List<ChartData> chartData : chartDataList) {
            if (chartData.size() == 0 || chartData.get(0).getLabel() == null) {
                model.addAttribute("chartData_y" + index, 0);
                model.addAttribute("chartData_x" + index, "");

            } else {
                model.addAttribute("chartData_y" + index, chartData.stream().map(c -> c.getValue()).collect(Collectors.toList()));
                model.addAttribute("chartData_x" + index, chartData.stream().map(c -> c.getLabel()).collect(Collectors.toList()));
                model.addAttribute("x_title" + index, xTitles.get(index - 1));
                model.addAttribute("y_title" + index, yTitles.get(index - 1));
                model.addAttribute("chartTitle" + index, chartTitles.get(index - 1));
            }

            index++;
        }


    }
}


