package com.jonathanracioppi.jonathanracioppicom.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "RESUMES")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    @Id
    private long resumeId;

    @Column
    private String namePerson;

    @Column
    private String emailPerson;

    @Column
    private String phoneNumber;

    @Column
    private String aboutPerson;

    @OneToMany()
    private List<ResumeSection> resumeSection;

    @Column
    private boolean display = true;


}
