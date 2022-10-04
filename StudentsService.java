package com.example.student.service;


import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {

    @Autowired
    StudentRepository studentRepository;

    public Student saveStudentToDb(StudentDto studentDto){

        Student student=new Student();
        student.setName(studentDto.getName());
        student.setDepartment(studentDto.getDepartment());
        student.setMarks(studentDto.getMarks());
        student.setHod(studentDto.getHod());
        student.setDob(studentDto.getDob());

        return studentRepository.save(student);


    }
    public  Student getDetails(int id){
        Student student=studentRepository.findById(id);
        if(student !=null){
            return student;
        }
        else {
            return null;
        }
    }
//
//    public List <Student>findByDepartment(String department){
//        Student student= (Student) studentRepository.findByDepartment(department);
//        if(student !=null){
//            return (List<Student>) student;
//        }
//        else {
//            return null;
//        }
//    }

    public List<Student>findAll(){
        return studentRepository.findAll();
    }
//
    public List<Student>findByDepartment(){
        return studentRepository.findByDepartment("CS");
    }

    public Student updateStudentDetails(int id, StudentDto studentDto) throws ResourceNotFoundException {
        Student student=studentRepository.findById(id);
        if(student!=null){
            student.setName(studentDto.getName());
            student.setDepartment(studentDto.getDepartment());
            student.setMarks(studentDto.getMarks());
            student.setDob(studentDto.getDob());
            student.setHod(studentDto.getHod());
            studentRepository.save(student);
        }
        else{
            throw new ResourceNotFoundException("40010","ALREADY UPDATED",null);
        }
        return student;
    }

    public void deleteStudentRecord(int id) throws ResourceNotFoundException {
        Student student=studentRepository.findById(id);
        if(student!=null){
            studentRepository.delete(student);
        }
        else{
            throw new ResourceNotFoundException("4001","no student record found", null);
        }
    }

    public List<String>getAllStudentByDepartmentAndMarks(String department, int marks) {

        return studentRepository. getAllNamesByDept(marks,department);
    }
}
