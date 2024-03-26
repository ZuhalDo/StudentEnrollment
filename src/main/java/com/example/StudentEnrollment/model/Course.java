package com.example.StudentEnrollment.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;

import java.util.List;


@Table(name = "courses")
@Entity
@Data
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "enrollment_count")
    private int enrollmentCount;

    @Column(name = "credits")
    private int credits;

    @Column(name = "semester")
    private String semester;

    @Column(name = "status")
    private String status;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students=new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", enrollmentCount=" + enrollmentCount +
                ", credits=" + credits +
                ", semester='" + semester + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


     @Transient // This annotation marks the field as transient, meaning it won't be persisted in the database
    private Long instructorId;


}
