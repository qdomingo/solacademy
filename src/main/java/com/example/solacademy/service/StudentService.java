package com.example.solacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solacademy.mapper.ScheduleMapper;
import com.example.solacademy.mapper.StudentMapper;
import com.example.solacademy.model.Student;
import com.example.solacademy.model.Task;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private ScheduleMapper scheduleMapper;

    public List<Student> getAllStudents() {
    	List<Student> studentlist = studentMapper.getAllStudents();
    	for(Student student: studentlist) {
    		List<Task> studentTasks = studentMapper.getTasksByStudentId(student.getId());
    		student.setTasks(studentTasks);
    	}
        return studentlist;
    }
    
    public List<Student> getStudentById(Long id) {
    	List<Task> studentTasks = studentMapper.getTasksByStudentId(id);
    	List<Student> student = studentMapper.getStudentById(id);
    	if(!student.isEmpty()) {
    		student.get(0).setTasks(studentTasks);
    	}
        return student;
    }
    
    public int createStudent(Student student) {
    	student.setId(studentMapper.getMaxStudentID());
        return studentMapper.createStudent(student);
    }
    
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }
    
    public int deleteStudent(Long id) {
    	int deleteSchedulesStudent = scheduleMapper.deleteStudentSchedule(id);
    	int deleteTasksByStudent = studentMapper.deleteTasksByStudent(id);
    	int deleteStudent = studentMapper.deleteStudent(id);
        return deleteSchedulesStudent + deleteTasksByStudent +deleteStudent;
    }
    
    public List<Task> getTasksByStudentId(Long id) {
        return studentMapper.getTasksByStudentId(id);
    }
    
    public int createTask(Task task) {
    	task.setId(studentMapper.getMaxTaskID());
        return studentMapper.createTask(task);
    }
    
    public int updateTask(Task task) {
        return studentMapper.updateTask(task);
    }
    
    public int deleteTask(Long id) {
        return studentMapper.deleteTask(id);
    }

}
