package com.sm.ems.lecturer.entity;

import com.sm.ems.course.entity.Course;
import com.sm.ems.department.entity.Department;
import com.sm.ems.security.entity.Auditable;
import com.sm.ems.util.MethodOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@ToString(exclude = {"courses"})
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Table(uniqueConstraints = @UniqueConstraint(name = "lecturer_email_unique", columnNames = "email"))
public class Lecturer extends Auditable<String> {

    @Id
    @SequenceGenerator(sequenceName = "lecturer_sequence", name = "lecturer_sequence", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecturer_sequence")
    private Long id;
    private String title;
    private String firstName;
    private String lastName;

    @Size(max = 10, min = 10, message = "Phone number should be of 10 digits!!")
    @Pattern(regexp = "^[0-9]{10}", message = "Mobile number is invalid!!")
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    private List<Course> courses;


    @MethodOrder.Order(value = 1)
    public Long getId() {
        return id;
    }

    @MethodOrder.Order(value = 2)
    public String getTitle() {
        return title;
    }

    @MethodOrder.Order(value = 3)
    public String getFirstName() {
        return firstName;
    }

    @MethodOrder.Order(value = 4)
    public String getLastName() {
        return lastName;
    }

    @MethodOrder.Order(value = 5)
    public String getPhone() {
        return phone;
    }

    @MethodOrder.Order(value = 6)
    public String getEmail() {
        return email;
    }


    public Department getDepartment() {
        return department;
    }

    @MethodOrder.Order(value = 7)
    public String getDepartment_() {
        return department.getDisplayName();
    }


    public Qualification getQualification() {
        return qualification;
    }

    @MethodOrder.Order(value = 8)
    public String getQualification_() {
        return qualification.getName();
    }


    public List<Course> getCourses() {
        return courses;
    }

    @MethodOrder.Order(value = 9)
    public List<String> getCourses_() {
        return courses.stream().map(c -> c.getDisplayName()).toList();
    }

    public String getDisplayName() {
        return title + " " + firstName + " " + lastName;
    }
}
