package com.sm.ems.department.entity;

import com.sm.ems.course.entity.Course;
import com.sm.ems.security.entity.Auditable;
import com.sm.ems.util.MethodOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Department extends Auditable<String> {

    @Id
    @SequenceGenerator(sequenceName = "department_sequence", name = "department_sequence", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    private Long id;
    @NotBlank(message = "Please add department name")
    private String name;
    private String code;
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Set<Course> courses;

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
    public String getAddress() {
        return address;
    }


    public Set<Course> getCourses() {
        return courses;
    }

    @MethodOrder.Order(value = 5)
    public List<String> getCourses_() {
        return courses.stream().map(c -> c.getDisplayName()).toList();
    }


    public String getDisplayName() {
        return code + " - " + name;
    }
}
