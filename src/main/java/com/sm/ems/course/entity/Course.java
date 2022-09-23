package com.sm.ems.course.entity;

import com.sm.ems.department.entity.Department;
import com.sm.ems.lecturer.entity.Lecturer;
import com.sm.ems.security.entity.Auditable;
import com.sm.ems.student.entity.Student;
import com.sm.ems.util.MethodOrder;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Builder
@ToString(exclude = {"students", "lecturer", "department"})
@EqualsAndHashCode(exclude = {"students", "lecturer"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Course extends Auditable<String> {

    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @Id
    private Long id;
    private String name;
    private String code;
    private int credits;

    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;


    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    private Lecturer lecturer;


    // @ManyToMany(mappedBy = "courses")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "course_student",
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Student> students;


    @ManyToOne
    private Department department;


    public void addStudent(Student student) {
        if (students == null)
            students = new HashSet<>();
        if (!students.contains(student))
            students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);

    }

    @PreRemove
    public void removeAllStudents() {
        students.clear();
    }

    @MethodOrder.Order(value = 1)
    public Long getId() {
        return id;
    }

    @MethodOrder.Order(value = 2)
    public String getName() {
        return name;
    }

    @MethodOrder.Order(value = 3)
    public String getCode() {
        return code;
    }

    @MethodOrder.Order(value = 4)
    public int getCredits() {
        return credits;
    }

    //@MethodOrder.Order(value = 5)
    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }


    public Department getDepartment() {
        return department;
    }


    public Lecturer getLecturer() {
        return lecturer;
    }

    @MethodOrder.Order(value = 5)
    public String getDepartment_() {
        return department.getDisplayName();
    }

    @MethodOrder.Order(value = 6)
    public String getLecturer_() {
        return lecturer.getDisplayName();
    }


    public Set<Student> getStudents() {
        return students;
    }

    @MethodOrder.Order(value = 8)
    public List<String> getStudents_() {
        return students.stream().map(s -> s.getDisplayName()).toList();
    }

    public String getDisplayName() {
        return code + " - " + name;
    }
}
