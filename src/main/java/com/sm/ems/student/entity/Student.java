package com.sm.ems.student.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sm.ems.course.entity.Course;
import com.sm.ems.security.entity.Auditable;
import com.sm.ems.util.MethodOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"})
@Table(name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(name = "student_emailid_unique", columnNames = "email_address")

)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends Auditable<String> {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private long id;
    private String firstName;
    private String lastName;
    @Column(name = "email_address", nullable = false)
    private String emailId;
    private String address;
    @Size(max = 10, min = 10, message = "Phone number should be of 10 digits!!")
    @Pattern(regexp = "^[0-9]{10}", message = "Phone number is invalid!!")
    private String phone;
    @Past(message = "Birth date should be in the past!!")
    private Date dob;
    @PastOrPresent(message = "Registration date cannot be in the future!!")
    private Date regDate;
    @Embedded
    private Guardian guardian;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "course_student",
//            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
//

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private Set<Course> courses;

    public void addCourse(Course c) {
        if (courses == null)
            courses = new HashSet<>();

        courses.add(c);
        c.getStudents().add(this);

    }

    @PreRemove
    public void removeStudentFromCourses() {
        for (Course c : courses)
            c.removeStudent(this);

    }

    @MethodOrder.Order(value = 1)
    public long getId() {
        return id;
    }

    @MethodOrder.Order(value = 2)
    public String getFirstName() {
        return firstName;
    }

    @MethodOrder.Order(value = 3)
    public String getLastName() {
        return lastName;
    }

    @MethodOrder.Order(value = 4)
    public String getEmailId() {
        return emailId;
    }

    @MethodOrder.Order(value = 5)
    public String getAddress() {
        return address;
    }

    @MethodOrder.Order(value = 6)
    public String getPhone() {
        return phone;
    }

    @MethodOrder.Order(value = 7)
    public Date getDob() {
        return dob;
    }

    @MethodOrder.Order(value = 8)
    public Date getRegDate() {
        return regDate;
    }

    @MethodOrder.Order(value = 9)
    public Guardian getGuardian() {
        return guardian;
    }


    public Set<Course> getCourses() {
        return courses;
    }

    @MethodOrder.Order(value = 10)
    public List<String> getCourses_() {
        return courses.stream().map(c -> c.getDisplayName()).toList();
    }


    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
