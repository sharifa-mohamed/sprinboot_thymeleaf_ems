package com.sm.ems.course.entity;

import com.sm.ems.security.entity.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial extends Auditable<String> {

    @Id
    @SequenceGenerator(name = "course_material_sequence", sequenceName = "course_material_sequence", initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long id;
    private String url;

    @OneToOne(cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

}
