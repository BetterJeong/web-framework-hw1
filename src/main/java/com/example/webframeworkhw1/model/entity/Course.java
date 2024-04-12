package com.example.webframeworkhw1.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {

    @Column(name="year")
    Integer year;

    @Column(name="semester")
    Integer semester;

    @Id
    @Column(name="subject_code")
    String subjectCode;

    @Column(name="subject_name")
    String subjectName;

    @Column(name="subject_type")
    String subjectType;

    @Column(name="professor")
    String professor;

    @Column(name="credit")
    Integer credit;

}
