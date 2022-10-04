package com.example.student.repository;

import com.example.student.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findById(int id);

   List<Student> findByDepartment(String department);

    List<Student>findAll();

    @Query("SELECT name FROM Student WHERE department=:department and marks>:marks")
    List<String>getAllNamesByDept(int marks, String department);


}
