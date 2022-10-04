package com.example.student.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name ="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name ="student_name")
    private String name;
    @Column
    private String department;
    @Column
    private int marks;
    @Column
    private String hod;

    @Column
    private String dob;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_joining")
    private Date date_of_joining=new Date(System.currentTimeMillis());

}
