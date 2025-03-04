package com.example.solacademy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.solacademy.model.Student;
import com.example.solacademy.model.Task;

@Mapper
public interface StudentMapper {

	List<Student> getAllStudents();
	List<Student> getStudentById(Long id);
	int createStudent(Student student);
	int updateStudent(Student student);
	int deleteStudent(Long id);
	Long getMaxStudentID();
	
	Long getMaxTaskID();
	List<Task> getTasksByStudentId(Long id);
	int createTask(Task task);
	int updateTask(Task task);
	int deleteTask(Long id);
	int deleteTasksByStudent(Long id);
	
}


