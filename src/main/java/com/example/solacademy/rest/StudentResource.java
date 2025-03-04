package com.example.solacademy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.solacademy.model.Student;
import com.example.solacademy.model.Task;
import com.example.solacademy.service.StudentService;

@RestController
@CrossOrigin(origins = {"http://solacademy.qdomingo.com", "http://localhost:4200", "http://localhost:4300"})
@RequestMapping("/api/students")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/getStudentById/{id}")
    public List<Student> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(Long.parseLong(id, 10));
    }
    
    @PostMapping("/createStudent")
    public int createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    
    @PostMapping("/updateStudent")
    public int updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }
    
    @PostMapping("/deleteStudent")
    public int deleteStudent(@RequestBody String id) {
        return studentService.deleteStudent(Long.parseLong(id, 10));
    }
    
    @PostMapping("/createTask")
    public int createTask(@RequestBody Task task) {
        return studentService.createTask(task);
    }
    
    @PostMapping("/updateTask")
    public int updateTask(@RequestBody Task task) {
        return studentService.updateTask(task);
    }
    
    @PostMapping("/deleteTask")
    public int deleteTask(@RequestBody String id) {
        return studentService.deleteTask(Long.parseLong(id, 10));
    }

}
