package com.sm.ems.student.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
        @AttributeOverride(name = "phone", column = @Column(name = "guardian_phone")),
        @AttributeOverride(name = "email", column = @Column(name = "guardian_email"))
})
public class Guardian {

    private String name;
    private String phone;
    private String email;
    private String relationshipToStudent;

}

