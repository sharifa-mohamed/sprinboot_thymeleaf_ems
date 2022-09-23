package com.sm.ems.lecturer.entity;

import com.sm.ems.security.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Qualification extends Auditable<String> {

    @Id

    @SequenceGenerator(sequenceName = "qualification_sequence", name = "qualification_sequence", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qualification_sequence")
    private Long id;
    private String name;


}
