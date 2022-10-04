package com.example.student.controller;

import com.example.student.dto.Details;
import com.example.student.dto.DriverPerformanceDto;
import com.example.student.dto.HelloWorld;
import com.example.student.dto.StudentDto;
import com.example.student.entity.DriverPerformance;
import com.example.student.entity.Student;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.service.DriverPerformanceService;
import com.example.student.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class studentController {

    @Autowired
    StudentsService studentsService;
    @Autowired
    DriverPerformanceService driverPerformanceService;

    @GetMapping("/student/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

//        http://localhost:8080/student/hello-world     // api for returns a Hello World string

    //
    @GetMapping("/student/hello-world-beam")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Hello World");
//
//        http://localhost:8080/student/hello-world-beam  // api for Returns a HelloWorld bean with message as Hello world
    }

    @GetMapping("/student/Details")
    public Details details() {
        return new Details("dinesh", "EEE", 100);
    }

//    http://localhost:8080/student/Details


    @GetMapping("/student/hello-world-bean/{name}")
    public HelloWorld helloWorldPathVariable(
            @PathVariable(value = "name") String studentName) {
        return new HelloWorld(String.format("Hello %s",studentName));
    }

    @PostMapping("/student/save")
    public Student saveStudent(@RequestBody StudentDto studentDto){
       return studentsService.saveStudentToDb(studentDto);

    }

//    @GetMapping("student/details/{id}")
//    public Student getStudentDetails(
//            @PathVariable(value = "id") Integer studentId) {
//        return studentsService.getDetails(studentId);

//        http://localhost:8080/student/details/3(id)
//    }

    @GetMapping("student/department/{department}")
    public List<Student> getStudentList(
            @PathVariable(value = "department") String studentDepartment){
        return studentsService.findByDepartment();
    }

    @GetMapping( "/student/details")
    public List<Student>getAllDetails(){
        return studentsService.findAll();

//        http://localhost:8080/student/details
//        http://localhost:8080/h2-console/
    }


    //        TO CHECK EXCEPTION HANDLING
    @GetMapping("student/details/{id}")
    public ResponseEntity<Object>getStudentDetails(
            @PathVariable(value = "id")int studentId){
        Student student=studentsService.getDetails(studentId);
        if (student == null){
            return new ResponseEntity<>("no student found", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
    }

//FOR UPDATE STUDENT INFO
    @PutMapping("student/update/{id}")
    public Object updateStudent(@PathVariable int id,
                                @RequestBody StudentDto studentDto){
        try {
            return studentsService.updateStudentDetails(id,studentDto);
        } catch (ResourceNotFoundException e) {
          return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

//     http://localhost:8080/student/update/1

    }

//    FOR DELETE STUDENT INFO

    @DeleteMapping("student/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id){
        try {
            studentsService.deleteStudentRecord(id);
            return new ResponseEntity<>("student record deleted",HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
//        http://localhost:8080/student/delete/1
    }

    @GetMapping("/student/names/{department}")
    public List<String> findStudentByDept(@PathVariable String department,
                                          @RequestParam(value = "marks", required = true)int marks){

        return studentsService.getAllStudentByDepartmentAndMarks(department,marks);
        }


        @PostMapping("fleets/simulate/save")
    public DriverPerformance saveDriverPerformance(@RequestBody DriverPerformanceDto driverPerformanceDto){
        return driverPerformanceService.saveDriverPerformanceToDb(driverPerformanceDto);
        }
    }






