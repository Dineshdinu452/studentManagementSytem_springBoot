package com.example.student.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentDto {

    private String name;
    private String department;
    private int marks;
    private String hod;
    private String dob;
}
