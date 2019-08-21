package com.springdemo.rest;

import com.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;





    @PostConstruct
    public void loadData(){
        students=new ArrayList<>();
        students.add(new Student("Poornima","Patel"));
        students.add(new Student("Mario","Rossi"));
        students.add(new Student("Mary","Smith"));
    }

    //define endpoint for "/students" -return list of students

    @GetMapping("/student")
    public List<Student> getStudents(){

        return students;

    }

    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if(studentId<0||studentId>=students.size())
            throw new StudentNotFoundException("Student id not found- "+studentId);
        return students.get(studentId);
    }
}
